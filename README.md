# Performance

Assuming you have Gradle (http://www.gradle.org) installed you should need to clone this repository, go into the directory and use: 

    gradle eclipse

which will create the necessary files to import your project into Eclipse. You can also use:

    gradle cleanEclipse eclipse

to regenerate your Eclipse project and classpath. When you're ready try:

    gradle jettyRun

to run your creation in Jetty.
