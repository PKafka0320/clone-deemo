# projectRhythm

## Settings for running program
1. Download javafx jdk
2. Create user library. (Window > Preferences > Java > Build Path > user Libraries)
3. Add javafx jar files in the user library.
4. Apply and close.
5. Add created library in project. (Project > Properties > Java Build Path > Libraries > Add Library)
6. Add run configuration arguments. (Run > Run Configuration > Arguments)
   <br> VM arguments: --module-path "{javafx directory path}\lib" --add-modules javafx.controls,javafx.fxml,javafx.media

# Class source not found problem
1. click "Attach Source...".
2. add external location of "src.zip" file in javafx directory.

### More Information for running program
https://luminitworld.tistory.com/44
