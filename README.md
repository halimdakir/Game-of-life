# **Game of life - TDD**

#### `` Download JavaFx SDK 15.``

#### ``The app can run without `module-info.java`, but if the app could not compile without it, then you have to create new `module-info.java` file and add this to it :``
 
 ```
module org.openjfx {
    requires javafx.controls;
    exports org.openjfx;
}
```