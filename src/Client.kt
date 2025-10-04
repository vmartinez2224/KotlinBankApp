class Client(val name: String) {
    private var balance: Double = 0.0

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("$name deposited $$amount. Current balance: $$balance")
        } else {
            println("The amount must be greater than 0.")
        }
    }

    fun withdraw(amount: Double) {
        if (amount > 0 && amount <= balance) {
            balance -= amount
            println("$name withdrew $$amount. Current balance: $$balance")
        } else {
            println("Withdrawal failed. Invalid amount or insufficient funds.")
        }
    }

    fun printInfo() {
        println("Client: $name | Balance: $$balance")
    }

    fun getBalance(): Double {
        return balance
    }
}
