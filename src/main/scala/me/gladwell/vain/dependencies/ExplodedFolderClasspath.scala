package me.gladwell.vain.dependencies

import java.io.File
import scala.collection.Traversable
import scala.reflect.macros.Context
import scala.sys.process.Process
import me.gladwell.vain.Configuration
import java.util.zip.ZipInputStream
import java.io.FileInputStream
import me.gladwell.vain.Logging
import java.io.FileOutputStream
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.charset.Charset
import java.nio.file.StandardOpenOption
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.util.jar.JarFile

trait ExplodedFolderClasspath extends Classpath with Logging {
  this: Configuration =>

  def addToClasspath(dependencies: Traversable[File]) = {
    dependencies.foreach { jarFile =>
      {
        val jar = new JarFile(jarFile)
        import scala.collection.JavaConversions._
        jar.entries.foreach{ entry =>
          val file = new java.io.File(compileClasspath + java.io.File.separator + entry.getName());
          if (entry.isDirectory()) {
            file.mkdirs()
          } else {
            log(s"extracting to ${file.getAbsolutePath}")
            val in = jar.getInputStream(entry)
            val out = new FileOutputStream(file)
            while (in.available() > 0) {
              out.write(in.read());
            }
            out.close();
            in.close();
          }
        }
      }
    }
  }
}

