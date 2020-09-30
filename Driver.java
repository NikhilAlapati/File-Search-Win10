/**
 * driver class for the Find File class
 *
 * @author Nikhil Alapati
 */
public class Driver {
    public static void main(String[] args) throws ItemNotFoundException {
        boolean passed = true;
        FindFile f = new FindFile(2);
        // finds the Program Files folder in my pc which is located in C drive
        f.directorySearch("Windows", "C:\\");
        // prints all the instances
        String[] files = f.getFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
        // test for invalid max files input
        try {
            FindFile f1 = new FindFile(0);
            System.out.println("MAXFILES CANNOT BE 0 REVISE CODE");
            passed = false;
        } catch (IllegalArgumentException e) {
            System.out.println("TEST PASSED maxFiles cannot be less than 1");
            System.out.println(".");
        }
        // test for invalid directory
        try {
            FindFile f2 = new FindFile(1);
            f2.directorySearch("Whatever File", "INVALID //*DIRECTORY*/., asgdkasbjhasfuasadsc");
            System.out.println("INVALID DIRECTORY PASSED SUCCESSFULLY REVISE CODE");
            passed = false;
        } catch (Exception e) {
            System.out.println("TEST PASSED INVALID DIRECTORY");
            System.out.println(".");
        }
        // this test will take a while it tests if the file doesnt exist because it has to search the whole pc before
        // determining the file doesnt exist
        try {
            FindFile f3 = new FindFile(1);
            f3.directorySearch("sdahdhansdas**INVALID FILE", "C:\\");
            f3.getFiles();
            passed=false;
        } catch (ItemNotFoundException e) {
            System.out.println("TEST PASSED FILE NOT FOUND");
            System.out.println(".");
        }
        // if all the tests passed it prints all tests passed
        if (passed) {
            System.out.println("ALL TESTS PASSED");
            System.out.println(".");
        }
    }
}
