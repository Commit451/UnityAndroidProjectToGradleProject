:: Assembles the jar
@echo off

::http://stackoverflow.com/questions/18896154/calling-gradle-from-bat-causes-batch-execution-to-stop
call gradlew clean unitytogradle:fatJar
copy unitytogradle\build\libs\unitytogradle-all-1.0.jar unitytogradle.jar