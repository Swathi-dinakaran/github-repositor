import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
public class Directory {
    public static void main(String args[])
    {
        Path dir = Paths.get("/Users/swathi/Training_projects/");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
        for (Path file: stream) {
        BasicFileAttributes attr;
        attr = Files.readAttributes(file,BasicFileAttributes.class);
        if(attr.isDirectory())
        {
            Path dir1 = Paths.get("/Users/swathi/Training_projects/"+file.getFileName()+"/");
        try (DirectoryStream<Path> stream1 = Files.newDirectoryStream(dir1)) {
        for (Path file1: stream1) {
            System.out.println(file1.getFileName());
        }}
        }   
        else
        
        System.out.println(file.getFileName());
    }
} catch (IOException | DirectoryIteratorException x) {
    // IOException can never be thrown by the iteration.
    // In this snippet, it can only be thrown by newDirectoryStream.
    System.err.println(x);
}

    }
    
}

