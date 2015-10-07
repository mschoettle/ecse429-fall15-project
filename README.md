# ECSE 429 Software Validation Project, Fall 2015
TouchCORE tool project (subset) for the ECSE429 Software Validation, Fall 2015 course at McGill University.
TouchCORE is a multitouch-enabled tool for concern-oriented software design modeling aimed at developing scalable and reusable software design concern models. The version provided here only contains functionality to create *Reusable Aspect Models* (RAM), or *Aspects*, which is a multi-view modelling notation supporting structure and behaviour using class, sequence and state diagrams (here called structure, message and state views). The goal of this project is to test parts of the message views. The full version can be found at [touchcore.cs.mcgill.ca](http://touchcore.cs.mcgill.ca).

## How to get started

To get started, all you need is the code contained in this repository and your development environment. 

* **Getting the projects/code**: To get the code, you can
    * fork this repository, or
    * import the projects from this repository into your own.
* **Set up the development environment**: Please follow **all** the instructions on [How To Setup](SETUP.md).

## Running TouchCORE

To run TouchCORE, run the class `ca.mcgill.sel.ram.ui.TouchCORE`.

The `Settings.txt` in `ca.mcgill.sel.ram.gui` allows you to adjust the window settings (fullscreen and window size). Also, you might need to adjust the VM arguments to grant TouchCORE more memory in case of performance issues.

**Note:** TouchCORE requires Java 7 or higher and a graphics card supporting OpenGL 2 (for the UI).

## Viewing the Metamodel

To view the different parts of the metamodel the *Sirius* editor is used. Do the following:
* Open the *Model Explorer* view in Eclipse via *Window* > *Show View* > *Other* > *Sirius* > *Model Explorer*
    * Note: This view is also available in the *Modeling* perspective.
* This view shows you the projects and allows to open an `.aird` file.
* Navigate to `ca.mcgill.sel.ram/model/` and double-click on `RAM.aird`.
* Expand `RAM.aird` and navigate to *Representations per category* > *Design* > *Entities*
* Open any of the entries starting with `RAM_`.

## Interacting with the User Interface

In general, TouchCORE supports multitouch, but also keyboard and mouse. 

In the instructions below, **tap** refers to both, a tap using a touchscreen and a click using the mouse. **Tap-and-hold** refers to clicking the left mouse button and keeping it pressed until the circle on the screen fills up completely.

Generally, elements (or properties of a model) in the user interface allow the following gestures:

* Double-tap allows editing of that property by either entering/changing text or using a selector shown to choose.
* Tap-and-hold on an element provides a menu with additional options for that element (see below for more details).
* Some UI elements allow a *unistroke gesture*, which is indicated by a yellow line. Tap and hold, then move while still holding down. For example, drawing a rectangle on the background of the class diagram allows to create a class. See below for more information (sometimes referred to as *draw a line*).

## Creating and Modifying Models

First, you need to create *concern*, which can contain several *Aspects*. You can view the *concern* like a project. Each *concern* has its own folder and a `.core` file. Each *Aspect* of that concern should be located in that folder and is serialized into a `.ram` file.

#### To create a concern
* Tap the `+` button.
* Create a folder for the concern (the name of the folder will be the name of the concern).
* Select the folder.
* The concern will then be opened.
 
#### To create an aspect
* Tap the `A+` button.
* Rename the aspect by double-tapping on `Untitled` in the top left corner.
* Save.

##### To create classes
* Tap-and-hold on the background of an aspect (alternatively, you can draw a rectangle).
* Select *Class* and type in a name.
* Double-tap on the name to rename the class afterwards.

##### To create an association between two classes
* Select one class
* Double-tap the other class
* Alternatively, you can draw a line from the edge of one class on top of the other class.
* To modify, double-tap on specific properties or tap-and-hold on them to choose advanced options.
    * For example, tap-and-holding on the end allows to toggle navigability.

##### To create attributes and operations:
* Tap-and-hold on the class
    * **Hint:** If you want to know what the icons mean, tap-and-hold on the inner circle to toggle showing the labels.
* Select one of the following options:
    * **`C+`** to create a constructor (named `create`),
    * **`O+`** to create an operation (type in the signature in one line as shown by the placeholder),
        * *visibility*: +, -, ~ or # (public, private, package or protected)
        * *return type*: One of the primitive types or one of your class names
        * *parameter*: type (see return type) and name
    * **`A+`** to create an attribute (type declaration in one line without the visibility).
* To modify attributes and operations, double-tap on specific properties or tap-and-hold on them to choose advanced options.

##### To create or open a message view
* Tap-and-hold on an operation.
* Select the *Go to Message View* icon (the one without the asterisk).

##### To modify a message view
* To **create** a message
    * draw a line from a grey placeholder to either an existing lifeline or into empty space and then
    * select the property and/or the operation to call.
    * **Note:** Selecting a metaclass allows you to create new instances or call static operations.
* To create a self or reply message tap-and-hold on the grey placeholder.
* The available menu will also give you other options to create fragments.
* To **delete** a message, tap-and-hold on the message signature (the operation).
    * **Note:** If a lifeline will become empty due to deleting messages, it will be removed.
* The same applies to fragments.
* To modify Combined Fragments (*loop*, *alt*, *opt* etc.)
    * Double-tap on the kind (top left corner) to choose another
    * Tap-and-hold on the constraint to add more operands.

#### To navigate
* To go back to the concern, tap on the `<` button.
* To go back to the main screen, tap on the home icon.

#### To open existing models

* In the main screen, select the folder icon and choose the concern (`.core` file) to load.
* The *RAM Models* list shows all aspects.
* Double-tap on the one you want to open or create a new one.

## Test Code Hints

The following code snippets will be required or helpful for your test setup. For example, models you created with TouchCORE can be loaded programmatically.

#### Initializing

In order to be able to load existing models, the following code is necessary:

```
// Initialize ResourceManager.
ResourceManager.initialize();
// Initialize packages.
RamPackage.eINSTANCE.eClass();

// Register resource factories.
ResourceManager.registerExtensionFactory("ram", new RamResourceFactoryImpl());

// Initialize adapter factories.
AdapterFactoryRegistry.INSTANCE.addAdapterFactory(RamItemProviderAdapterFactory.class);
```

#### Loading an Aspect

```
Aspect aspect = (Aspect) ResourceManager.loadModel("path/to/the/concern/Aspect.ram");
```

#### Navigating a model

Once you loaded a model, you can navigate it to access its properties and children. Each property and reference in the metamodel has a corresponding *getter method*. Make use of the code completion provided by Eclipse or use debugging to look at the properties of a loaded model.

#### Getting a Message View

You can either access them directly by calling `aspect.getMessageViews()` to retrieve the list.
Or, you can request the message view for a specific operation by calling `RAMModelUtil.getMessageViewFor(Aspect, Operation)`.

#### Initializing the GUI

In addition to the initializing code provided above, the following is required to run the GUI of TouchCORE.

```
RamApp.initialize(new Runnable() {
        
    @Override
    public void run() {
        // TODO: Your code.
    }
});
```

The code inside `run()` will be executed once TouchCORE is fully set up. In there, you could, for example, create a `MessageViewView`. The following code snippets also shows you how to access the layout of a message view:

```
MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
ContainerMapImpl layout = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), messageView);

MessageViewView messageViewView = new MessageViewView(messageView, layout, 1024, 768);
```

To access a handler for a view, use the `ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory` class.

## Background

[TouchCORE](http://touchcore.cs.mcgill.ca). is developed by the [Software Engineering Lab](http://www.cs.mcgill.ca/~joerg/SEL/SEL_Home.html) at the [School of Computer Science](http://cs.mcgill.ca), McGill University. It's predecessor was [TouchRAM](http://touchcore.cs.mcgill.ca/TouchRAM.html).

TouchCORE is built using the [Eclipse Modeling Framework](http://www.eclipse.org/emf) for the backend and [Multitouch for Java (MT4j)](http://mt4j.org/) for the touch-based user interface.
