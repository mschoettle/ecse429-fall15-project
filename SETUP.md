# How to set up your IDE for TouchCORE

To get your IDE set up, import the code and be able to run TouchCORE, please follow **all** of these instructions.

## Installation

### Installing Java

You need Java 7 or higher. If you don't have it installed yet, install the latest version first. You can find it on [Oracle's website](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

It is highly recommended that you use the Java JDK. The Java Runtime Environment is not enough. The JDK contains documentation and the Java source, which will help you when developing and debugging.

### Installing Eclipse

Eclipse Mars is required for TouchCORE. While you may use your existing Eclipse installation, it is recommended to start off with the *Eclipse Modeling Tools*. If you feel comfortable enough, you can add these to your existing installation.

1. Download and install the *Eclipse Modeling Tools* package from [eclipse.org](http://www.eclipse.org/downloads/).
1. Unzip and start it.
1. Recommended: Create a new workspace for TouchCORE related development.
1. Go to *Preferences* > *General* > *Editors* > *Text Editors*
1. Optional: If you use Java 8 (recommended) and don't want to see the warning *"Build path specifies execution environment JavaSE-1.7. There are no JREs installed in the workspace that are strictly compatible with this environment."* do the following:

    *Preferences* > *Java* > *Compiler* > *Building* > *"No strictly compatible JRE [...]"* change to *Ignore* 

### Installing required plug-ins

The following plug-ins are required to be installed.

#### OCL Samples and Editors

The metamodel makes use of OCL for derivation, operation bodies and constraints.

1. Go to *Help* > *Install New Software*.
1. Select the release update site of the current Eclipse version (e.g., "*Mars - <URL>*" for Eclipse Mars).
1. Choose *OCL Samples and Editors* from the *Modeling* category.
1. Follow the instructions.

#### Acceleo
 
Acceleo is required by the code generation module of TouchCORE.

1. Go to *Help* > *Install Modeling Components*.
1. Select and install Acceleo.

#### Checkstyle ###

Checkstyle ([eclipse-cs](http://eclipse-cs.sourceforge.net/)) is used to maintain a consistent coding style across contributors within TouchCORE. You need to do the same to maintain a **consistent coding style** within your team and not make it harder for the evaluators/graders.

1. Install Checkstyle using the update site: http://eclipse-cs.sourceforge.net/update/
1. Follow the instructions.

## Importing the projects

Before you can import the projects into Eclipse, you need to clone your repository (if you forked on GitHub) or add the code to your repository (and then commit it). Since this is done by one team member, everyone else needs to clone the repository (or pull the changes) first.

### Using Eclipse ###

1. Open the *Git Repository Exploring* perspective.
1. [Clone the repository](http://wiki.eclipse.org/EGit/User_Guide#Cloning_a_Repository).
1. [Import projects](http://wiki.eclipse.org/EGit/User_Guide#Importing_projects) from the local (cloned) repository.

### Using the command-line

1. [Clone the repository](http://git-scm.com/book/en/Git-Basics-Getting-a-Git-Repository#Cloning-an-Existing-Repository) to a local location of your choice.
1. Import the projects into your workspace in Eclipse (see link above).

## Setting up Eclipse and the plug-ins

Now that Eclipse and the plug-ins are installed and the projects are imported, all that is left is some configuring.

### Setting up the Code Formatter

In order for Eclipse to support you with the coding style that is checked by Checkstyle, the *Java Code Formatter* needs to be configured.

1. Go to *Preferences* > *Java* > *Code Style* > *Formatter*.
1. Click on *Import*.
1. Select the file *touchram_formatter.xml* located in the project *ca.mcgill.sel.commons*.
1. Press *OK*.

### Setting up Checkstyle

1. Go to *Preferences* > *Checkstyle* and press *New*.
1. Select *Project Relative Configuration*.
1. Name: *TouchRAM Checks* (**use exactly this name [case-sensitive]**)
1. Location: */ca.mcgill.sel.commons/touchram_checkstyle.xml*
1. Click on *OK* and confirm rebuilding all projects.
1. Set this configuration as your default.
1. If you weren't asked to rebuild: Go to *Project* > *Clean...* and select *Clean all projects*.

### Configure Git

The code is maintained using the distributed version control system [Git](http://git-scm.com/). You can either use the command line, your local Git client or Eclipse, which by default has Git support ([EGit](http://www.eclipse.org/egit/)).

**Before you start** using Git you need to configure your username and email in order to be properly identified when committing.

Follow the [instructions to set up your identity](http://git-scm.com/book/en/Getting-Started-First-Time-Git-Setup#Your-Identity). Alternatively, you can do it directly in Eclipse as described in the [user guide](http://wiki.eclipse.org/EGit/User_Guide#Identifying_yourself).

You're done and can now run TouchCORE (see [README](README.md)).
