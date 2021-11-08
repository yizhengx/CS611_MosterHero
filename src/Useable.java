import java.util.*;

// Useable Interface: all items can be used should implement this interface
public interface Useable {
    // return a set of attr_affected and corresponding values
    Map<String, Integer> beUsed();
}