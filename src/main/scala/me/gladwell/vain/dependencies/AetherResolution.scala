package me.gladwell.vain.dependencies

import me.gladwell.vain.Logging
import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.file.FileTransporterFactory
import org.eclipse.aether.impl.DefaultServiceLocator
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.transport.http.HttpTransporterFactory
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.repository.RemoteRepository
import org.eclipse.aether.resolution.ArtifactRequest
import org.eclipse.aether.artifact.DefaultArtifact
import java.io.File
import me.gladwell.vain.Dep
import me.gladwell.vain.Configuration

trait AetherResolution extends Resolution {
  this: Configuration with Logging =>

  private val system = {
    val locator = MavenRepositorySystemUtils.newServiceLocator()
    locator.addService(classOf[RepositoryConnectorFactory], classOf[BasicRepositoryConnectorFactory])
    locator.addService(classOf[TransporterFactory], classOf[FileTransporterFactory])
    locator.addService(classOf[TransporterFactory], classOf[HttpTransporterFactory])

    //        locator.setErrorHandler( new DefaultServiceLocator.ErrorHandler()
    //        {
    //            def serviceCreationFailed( Class<?> type, Class<?> impl, Throwable exception )
    //            {
    //                exception.printStackTrace()
    //            }
    //        } )

    locator.getService(classOf[RepositorySystem])
  }

  private val session = {
    val session = MavenRepositorySystemUtils.newSession()
    val localRepo = new LocalRepository(localRepoPath)
    session.setLocalRepositoryManager(system.newLocalRepositoryManager(session, localRepo))

//    session.setTransferListener(new ConsoleTransferListener())
//    session.setRepositoryListener(new ConsoleRepositoryListener())

    session
  }

  private val remoteRepository = {
    new RemoteRepository.Builder( "central", "default", "http://repo1.maven.org/maven2/" ).build()
  }

  override def resolveDependencies(dependencies: Seq[Dep]): Seq[File] = {
    log(s"resolving dependencies=$dependencies using aether")
    dependencies.map(dependency => {
        val artifactRequest = new ArtifactRequest()
        artifactRequest.setArtifact(new DefaultArtifact(s"${dependency.group}:${dependency.name}:${dependency.version}"))
        artifactRequest.addRepository(remoteRepository)
        val artifactResult = system.resolveArtifact( session, artifactRequest )
        log(s"resolved dependency=${artifactResult.getArtifact.getFile} using aether")
        artifactResult.getArtifact.getFile
    })
  }

}
