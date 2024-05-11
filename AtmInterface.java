import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/*<applet code="AtmInterface.class" height=500 width=500></applet>*/

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

public class AtmInterface extends Applet implements ActionListener {
    private BankAccount account;

    private Label titleLabel;
    private Label balanceLabel;
    private TextField amountField;
    private Button withdrawButton;
    private Button depositButton;
    private Button balanceButton;
    private TextArea outputArea;

    private Image atmImage;

    public void init() {
        setBackground(Color.PINK);
        account = new BankAccount(1000); // Initial balance
        setLayout(new BorderLayout());
        Panel topPanel = new Panel(new GridLayout(1, 2));
        titleLabel = new Label("Sricharan's ATM System", Label.CENTER);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);
        balanceLabel = new Label("Initial balance: $" + account.getBalance(), Label.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(balanceLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel for Amount Field and Buttons
        Panel centerPanel = new Panel(new BorderLayout());
        Panel inputPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        Label enterAmountLabel = new Label("Enter amount: ");
        enterAmountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        inputPanel.add(enterAmountLabel);
        amountField = new TextField(10);
        inputPanel.add(amountField);
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        withdrawButton = new Button("Withdraw");
        depositButton = new Button("Deposit");
        balanceButton = new Button("Check Balance");
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 18));
        depositButton.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(balanceButton);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel for Output Area
        outputArea = new TextArea(20, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.BOLD, 20));
        add(outputArea, BorderLayout.SOUTH);
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        balanceButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (account.withdraw(amount)) {
                    outputArea.append("Withdrawal successful. New balance: $" + account.getBalance() + "\n");
                } else {
                    outputArea.append("Insufficient funds\n");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid amount\n");
            }
        } else if (e.getSource() == depositButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                outputArea.append("Deposit successful. New balance: $" + account.getBalance() + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid amount\n");
            }
        } else if (e.getSource() == balanceButton) {
            outputArea.append("Current balance: $" + account.getBalance() + "\n");
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}
