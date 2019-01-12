package business.messageModels;

public class HelloMessage {
    private String greeting;

    public HelloMessage() {
    }

    public HelloMessage(String greeting) {
        this.greeting = greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return this.greeting;
    }
}
