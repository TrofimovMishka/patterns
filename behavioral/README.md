# Learning about Strategy pattern
**Strategy is a behavioral design pattern that turns a set of behaviors into objects and makes them interchangeable inside original context object.**
The original object, called context, holds a reference to a strategy object. The context delegates executing the behavior to the linked strategy object. In order to change the way the context performs its work, other objects may replace the currently linked strategy object with another one.

*Java 8 brought the support of lambda functions, which can serve as simpler alternatives to the Strategy pattern.*

**Identification: Strategy pattern can be recognized by a method that lets a nested object do the actual work, as well as a setter that allows replacing that object with a different one.**
