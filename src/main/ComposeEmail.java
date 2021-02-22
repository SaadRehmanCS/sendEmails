package src.main;

import src.model.MailAuthorization;

import java.util.Scanner;

import static java.lang.Character.*;

public class ComposeEmail {

    public ComposeEmail() throws Exception, IncorrectEmailFormatException {
        MailAuthorization mail = new MailAuthorization();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter recipient email: ");
        boolean proceed = false;
        String recipientEmail = null;
        while (proceed == false) {
            try {
                recipientEmail = input.nextLine();
                catchEmailException(recipientEmail);
                proceed = true;

            } catch (IncorrectEmailFormatException ax) {
                System.out.println(ax);
                System.out.print("Retype recipient email: ");
            }
        }

        System.out.println("1 --> Greeting\n2 --> Custom message");
        int choice = input.nextInt();

        if (choice == 2) {
            input.nextLine();
            System.out.print("Enter email subject: ");
            String subject = input.nextLine();
            System.out.print("Enter email body(use \"\\n\" for new line): ");
            String body = input.nextLine();
            mail.sendEmail(recipientEmail, subject, body);
        } else {
            String subject = "Greetings from Saad!";
            String body = "Hello,\nIt's great to talk to you after so long! I'm looking forward to catching up"
                    + " once I figure out how to escape this computer program!";
            mail.sendEmail(recipientEmail, subject, body);
        }
    }

    public void catchEmailException(String email) throws IncorrectEmailFormatException {

        int counter = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                counter++;
            }
        }
        if (counter != 1 || !isLetter(email.charAt(0)) || !isLetter(email.charAt(email.length() - 1))) {
            throw new IncorrectEmailFormatException(email);
        }

        if (email.length() < 4 || !email.endsWith(".com") || email == null){
            throw new IncorrectEmailFormatException(email);
        }
    }


}

class IncorrectEmailFormatException extends Exception {

    String incorrectEmail;

    public IncorrectEmailFormatException(String incorrectEmail) {
        super(incorrectEmail);
        this.incorrectEmail = incorrectEmail;
        System.out.println(exceptionMessage());
    }

    public String exceptionMessage() {
        return "You have entered an illegal email format. Please try again";
    }
}