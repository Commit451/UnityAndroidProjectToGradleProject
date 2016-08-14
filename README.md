# UnityAndroidProjectToGradleProject
Takes the output project from Unity and converts it into a proper Gradle project that can be easily opened in Android Studio

[![Build Status](https://travis-ci.org/Commit451/ResourcesPoet.svg?branch=master)](https://travis-ci.org/Commit451/ResourcesPoet)

# Download
Download the latest jar [here](https://github.com/Commit451/UnityAndroidProjectToGradleProject/releases/latest)

# Basic Usage
To convert a project interactively:
```java
java -jar unitytogradle.jar
```
Then, follow the prompts.

If you are wanting to pass the path to the Unity output project to the program:
```java
java -jar unitytogradle.jar path/to/your/project
```
For that last argument, you can typically drag and drop the folder from Finder or Windows Explorer onto Terminal or CMD to make things quicker.

Your newly created project will be exported in a `gradle-project` folder in the same directory where you run the command. With this, you can open up Android Studio and use the "Open an existing Android Studio project" tool.

# Why Not Just Use Import?
Android Studio does allow for importing non gradle projects, as highlighted [here](https://docs.unity3d.com/Manual/android-BuildProcess.html) which works really well. The main goal for this script is that it would allow for more of an automated build process.

License
--------

    Copyright 2016 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
