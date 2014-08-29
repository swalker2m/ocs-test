import OcsKeys._

name := "edu.gemini.spModel.core"

unmanagedJars in Compile ++= Seq(
  new File(baseDirectory.value, "../../lib/bundle/org.scala-lang.scala-swing_2.10.1.v20130302-092018-VFINAL-33e32179fd.jar"),
  new File(baseDirectory.value, "../../lib/bundle/osgi.core-4.3.1.jar")
)

osgiSettings

ocsBundleSettings

OsgiKeys.bundleActivator := None

OsgiKeys.bundleSymbolicName := name.value

OsgiKeys.dynamicImportPackage := Seq("")

OsgiKeys.exportPackage := Seq(
  "edu.gemini.spModel.core",
  "edu.gemini.spModel.core.osgi")
        
sourceGenerators in Compile += Def.task {
  val ocsVer = ocsVersion.value
  val outDir = (sourceManaged in Compile).value / "edu" / "gemini" / "spModel" / "core"
  val outFile = new File(outDir, ocsVer.sourceFileName)
  outDir.mkdirs
  IO.write(outFile, ocsVer.toClass) // UTF-8 is default
  Seq(outFile)
}.taskValue


