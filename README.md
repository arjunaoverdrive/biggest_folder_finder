# biggest_folder_finder  
An app to find subdirectories exceeding a given file size in a given directory  
# Technologies used  
**Java, ForkJoinPool**  
# Usage    
To run the app, open a CLI/terminal session, navigate to the folder where the jar file is located,  
  and run the following command:  
  java -jar <path_to_jar/BiggestFolderFinder.jar -d <path_to_target_directory> -s <size>  
The -d switch accepts an absolute path to the target directory as its argument; 
  the -s switch accepts the threshold of the file size under which files will not end up in the output.  
  
