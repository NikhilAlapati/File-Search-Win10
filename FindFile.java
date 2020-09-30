import java.io.File;
import java.nio.file.Paths;

/**
 * this class finds a file using a given path and the user also provides a maxFiles which dicates how many
 * copies the algorithm finds
 *
 * @author Nikhil Alapati
 */
public class FindFile {
    // fields
    /*
     * Class invariant:
     * maxFiles has to be greater than 0
     */
    private String[] files;
    private int numElements = 0;
    private int maxFiles;

    /**
     * c'tor takes in maxFiles you want to find
     *
     * @param maxFiles
     */
    public FindFile(int maxFiles) {
        //checks if max files is valid
        if (maxFiles > 0) {
            // sets max Files
            this.maxFiles = maxFiles;
            // sets the array with maxFiles length
            this.files = new String[maxFiles];
        } else {
            // if maxFiles is not valid it throws an IllegalArgumentException
            throw new IllegalArgumentException("maxFiles cannot be less than 1");
        }

    }

    /**
     * this method checks if the path passed in is valid by checking if it has correct characters
     * and checks if the direcotry actually exists
     *
     * @param path
     * @return boolean
     */
    private static boolean isValid(String path) {
        File f = new File(path);
        //found the try catch part at //https://stackoverflow.com/questions/468789/is-there-a-way-in-java-to-determine-if-a-path-is-valid-without-attempting-to-cre
        try {
            // uses the Paths class and passes it to the get method which converts string into path
            // the Paths class throws an exception if it cant convert
            Paths.get(path);
            // this catches the thrown exception
        } catch (Exception ex) {
            // returns false if the exception is thrown
            return false;
        }
        // did this return statement on my own
        //after checking if the path has valid characters this statement checks if the path acutally exists
        // if yes it returns true else false
        return true && f.exists();
    }

    /**
     * Helper method that takes in target and direcotry name
     *
     * @param target
     * @param dirName
     */
    public void directorySearch(String target, String dirName) {
        /*
         *the pirpose of this helper method is so i don't have to check if the path is valid
         * every recursive call which wastes time
         * so by checking if the path is valid one time you dont have to check it again and again
         */

        // calls the isValid method and checks if its valid
        if (isValid(dirName) == true) {
            // if its valid it passes target and directory name to
            // the directorySearchFinal which searches for the file
            directorySearchFinal(target, dirName);
        } else {
            // if not valid throws an IllegalArgumentException
            throw new IllegalArgumentException("Invalid directory name");
        }
    }

    /**
     * private method that searches the file with given target and path
     *
     * @param target
     * @param dirName
     */
    private void directorySearchFinal(String target, String dirName) {
        // creates a new File object with the directory name passed in
        File path = new File(dirName);
        // checks if the path is a directory and that it doesn't have null files(Base Case)
        if (path.isDirectory() && path.listFiles() != null) {
            // uses a forEach loop to iterate through the current directory
            for (File f : path.listFiles()) {
                // checks if the current name is equal to the target
                if (f.getName().equals(target)) {
                    // adds the name of the file/folder to the array using substring
                    System.out.println(files[0]);
                    this.files[numElements] = f.toString().substring(0, f.getAbsolutePath().lastIndexOf("\\") + 1) +
                            f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1);
                    // increments the num elements
                    numElements++;

                }
                // checks if F is a directory
                if (f.isDirectory()) {
                    //checks if the desired maxFiles has been reached if yes it doesnt call the statement
                    // if no it calls the method again
                    if (numElements <= (maxFiles-1)) {
                        // calls this method with the same target but a different path(Recursive Case)
                        directorySearchFinal(target, f.getAbsolutePath());
                    }
                }


            }
        }
    }

    /**
     * returns the count of files found
     *
     * @return int
     */
    public int getCount() {
        return numElements;
    }

    /**
     * returns the Files Array
     *
     * @return String[]
     */
    public String[] getFiles() throws ItemNotFoundException {
        // checks if the array is empty if yes
        // it throws an ItemNotFoundException
        if (this.getCount() == 0) {
            throw new ItemNotFoundException("FILE NOT FOUND");
        }
        // if the total files found is less than max files it resizes the array
        if (this.numElements < this.maxFiles) {
            String[] n = new String[this.numElements];
            for (int i = 0; i < n.length; i++) {
                n[i] = this.files[i];
            }
            return n;
        }
        // returns the files
        return files;
    }
}
