package com.blogspot.howdoidothatinjava.tutorials;

import static com.blogspot.howdoidothatinjava.utilities.PrintUtilties.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.blogspot.howdoidothatinjava.sandbox.TestObject;

public class ObjectTest {
    
    public static void main(String[] args) {
        
        List<TestObject> objList = TestObject.generateTestObjectList();
        
        println("Objects in the list:", under);
        println();
        
        for (TestObject t: objList){
            t.displayObject();
        }
        
        println();
        
        //Ex. 1 - Hardcoded Result
        println("Ex. 1: Hardcoded Result", under);
        println();
        println("TestObject with a > 20 (hardcoded)");
        println();
        printObjectsWithAGreaterThanTwenty(objList);
        println();
        
        //Ex. 2 - Parameterized Range
        println("Ex. 2 - Parameterized Range", under);
        println();
        println("TestObject with b between 10 and 70 inclusive");
        println();
        printObjectsWithBInRange(objList, 10, 70, Inclusivity.BOTH);
        println();
        
        //Ex. 3 - Use of Local Class
        println("Ex. 3 - Use of Local Class", under);
        println();
        println("TestObject with starting with 'S' and a*3 <= b");
        println();
        
        class TestObjectForNameAndRange implements TestObjectValidator {

            public boolean test(TestObject t) {
                return t.getName().startsWith("S") && t.getA() * 3 <= t.getB();
            }
        }
        
        printTestObjects(objList, new TestObjectForNameAndRange());
        println();
        
        //Ex. 4 - Use of Anonymous Class
        println("Ex. 4 - Use of Anonymous Class", under);
        println();
        println("TestObject with starting with 'S' and a*3 <= b");
        println();
        
        printTestObjects(objList, new TestObjectValidator() {
            
            public boolean test(TestObject t) {
                return t.getName().startsWith("S") && t.getA() * 3 <= t.getB();
            }
        });
        println();
        
        //Ex. 5 - Use of Lambda
        println("Ex. 5 - Use of Lambda", under);
        println();
        println("TestObject with starting with 'S' and a*3 <= b");
        println();
        
        printTestObjects(objList, (TestObject t)->t.getName().startsWith("S") && t.getA() * 3 <= t.getB());
        println();
        
        //Ex. 6 - Use of Predicate in place of Lambda
        println("Ex. 6 - Use of Predicate in place of Lambda", under);
        println();
        println("TestObject with name containing 's', case insensitive");
        println();
        printTestObjectsWithPredicate(objList, t -> t.getName().toLowerCase().contains("s"));
        println();
        
        //Ex. 7 - Use of Predicate and Consumer Lambda
        println("Ex. 7 - Use of Predicate and Consumer Lambda", under);
        println();
        println("TestObject with name ending in 'y', case insensitive");
        println();
        processTestObjectWithPredicateAndConsumer(objList, t -> t.getName().toLowerCase().endsWith("y"), t -> t.displayObject());
        println();
        
        //Ex. 8 - Use of Predicate, Function and Consumer
        println("Ex. 8 - Use of Predicate, Function and Consumer", under);
        println();
        println("TestObject name where a >= b");
        println();
        processTestObjectWithPredicateFunctionAndConsumer(objList, t->t.getA() >= t.getB(), t -> t.getName(), name -> println(name));
        println();
        
        //Ex. 9 - Use of Generics To Add Further Flexibility
        println("Ex. 9 - Use of Generics To Add Further Flexibility", under);
        println();
        println("TestObject name where a < b");
        println();
        processObjects(objList, t -> t.getA() < t.getB(), t -> t.getName(), name -> println("TestOblject (" + name + ")"));
        println();
        
        //Ex. 10 - Use of Generics to Process Objects and Return Result Collection
        println("Ex. 10 - Use of Generics to Process Objects and Return Result Collection", under);
        println();
        println("TestObject count of names matched where a < b");
        println();
        List<String> results = processObjects(objList, t -> t.getA() < t.getB(), t -> t.getName());
        println("The number of results in List returned was " + results.size());
        println();
        
        //Ex. 11 - Use of Bulk Data Methods to Repeat Results in Ex. 10
        println("Ex. 11 - Use of Bulk Data Methods to Repeat Results in Ex. 10", under);
        println();
        println("TestObject count of names matched where a < b");
        println();
        results.clear();
        results = objList.stream().filter(t -> t.getA() < t.getB()).map(t -> t.getName()).collect(Collectors.toList());
        println("The number of results in List returned was " + results.size());
        println();
        
        //Ex. 12 - Use of Bulk Data Method Without Temporary Collection
        println("Ex. 12 - Use of Bulk Data Method Without Temporary Collection", under);
        println();
        println("TestObject count of predicate results to achieve same result as in Ex. 10 & Ex. 11");
        println();
        println("The number of results found in filter was: " + objList.stream().filter(t-> t.getA() < t.getB()).count());
        println();
        
        //Rx. 13 - Use of Named Predicates as Variables
        println("Rx. 13 - Use of Named Predicates as Variables", under);
        println();
        println("Use of named Predicate to get range where b > 10 and b < 35");
        println();
        Predicate<TestObject> p1 = t -> t.getB() > 10 && t.getB() < 35;
        processGenericObjects(objList, p1, t -> t.displayObject());
        println();
        println("Use of second named predicate to find names containing 'er'");
        println();
        Predicate<TestObject> p2 = t -> t.getName().toLowerCase().contains("er");
        processGenericObjects(objList, p2, t -> println("TestObject (" + t.getName() + ")"));
        println();
        
        //Ex. 14 - Use of Named Consumer to Customize Print Formatting
        println("Ex. 14 - Use of Named Consumer to Customize Print Formatting", under);
        println();
        println("Print TestObjects where name contains 'a' using named Consumer using TestObject's displayObject()");
        println();
        Consumer<TestObject> printStandard = t -> t.displayObject();
        processGenericObjects(objList, t -> t.getName().toLowerCase().contains("a"), printStandard);
        println();
        println("Print TestObjects where name contains 'a' using named Consumer using different print function");
        println();
        Consumer<TestObject> otherPrintFormat = t -> displayOtherFormat(t);
        processGenericObjects(objList, t -> t.getName().toLowerCase().contains("a"), otherPrintFormat);
    
    }

    private static void printObjectsWithAGreaterThanTwenty(List<TestObject> list) {
        // get each element in the colection and display it based on criteria
        for(TestObject t : list){
            if (t.getA() > 20) t.displayObject();
        }
    }
    
    private static void printObjectsWithBInRange(List<TestObject> list, int low, int high, Inclusivity rule) {
        for(TestObject t : list){
            switch (rule){
                case BOTH:
                    if(t.getB() >= low && t.getB() <= high) t.displayObject();
                    break;
                case END:
                    if(t.getB() > low && t.getB() <= high) t.displayObject();
                    break;
                case START:
                    if(t.getB() >= low && t.getB() < high) t.displayObject();
                    break;
                case NONE:
                    if(t.getB() > low && t.getB() < high) t.displayObject();
            }
        }
    }
    
    private static void printTestObjects(List<TestObject> list, TestObjectValidator validator){
        for(TestObject t : list){
            if (validator.test(t)) t.displayObject();
        }
    }
    
    private static void printTestObjectsWithPredicate(List<TestObject> list, Predicate<TestObject> filter){
        for(TestObject t : list){
            if(filter.test(t)) t.displayObject();
        }
    }
    
    private static void processTestObjectWithPredicateAndConsumer(List<TestObject> list, Predicate<TestObject> filter, Consumer<TestObject> voidFunction) {
        for (TestObject t : list){
            if(filter.test(t)) voidFunction.accept(t);
        }
        
    }
    
    private static void processTestObjectWithPredicateFunctionAndConsumer(List<TestObject> list, 
            Predicate<TestObject> filter, Function<TestObject,String> mapper, Consumer<String> voidFunction){
        for (TestObject t : list){
            if(filter.test(t)) {
                String out = mapper.apply(t);
                voidFunction.accept(out);
            }
        }
    }
    
    private static <T, U> void processObjects(Iterable<T> collection, Predicate<T> filter, Function<T, U> mapper, Consumer<U> voidFunction){
        for(T t : collection){
            if(filter.test(t)){
                U u = mapper.apply(t);
                voidFunction.accept(u);
            }
        }
    }
    
    private static <T, U> List<U> processObjects(Iterable<T> collection, Predicate<T> filter, Function<T,U> mapper){
        List<U> uList = new ArrayList<U>();
        for(T t : collection){
            if (filter.test(t)){
                uList.add(mapper.apply(t));
            }
        }
        return uList;
    }
    
    private static <T> void processGenericObjects(Collection<T> collection, Predicate<T> criteria, Consumer<T> voidFunction){
        collection.stream().filter(criteria).forEach(voidFunction);
    }
    
    private static void displayOtherFormat(TestObject t){
        println("\tTestObject (" + t.getName() + ")");
        println("\t\ta: " + t.getA());
        println("\t\tb: " + t.getB());
        println();
    }
    
    // Functional Interfaces
    interface TestObjectValidator {
        boolean test(TestObject t);
    }

    private enum Inclusivity {
        START, END, BOTH, NONE
    }
    
}
