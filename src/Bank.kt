import java.util.Scanner

class Bank {
    private val clients = mutableListOf<Client>()
    private val scanner = Scanner(System.`in`)

    init {
        // Default clients
        clients.add(Client("Leon S. Kennedy"))
        clients.add(Client("Luis Serra Navarro"))
        clients.add(Client("Ada Wong"))
        clients.add(Client("Ashley Graham"))
    }

    fun showMenu() {
        var option: Int

        do {
            println("\n===============================")
            println("BANK MENU")
            println("===============================")
            println("1. Deposit to a client")
            println("2. Withdraw from a client")
            println("3. Show a client's balance")
            println("4. Show all clients")
            println("5. Show total balance in the bank")
            println("6. Add a new client")
            println("0. Exit")
            print("Select an option: ")

            option = scanner.nextInt()
            scanner.nextLine() // clear buffer

            when (option) {
                1 -> depositToClient()
                2 -> withdrawFromClient()
                3 -> showClientInfo()
                4 -> showAllClients()
                5 -> showTotalBalance()
                6 -> addClient()
                0 -> println("Exiting the system...")
                else -> println("Invalid option. Please try again.")
            }
        } while (option != 0)
    }

    private fun selectClient(): Client? {
        if (clients.isEmpty()) {
            println("There are no registered clients.")
            return null
        }

        println("\n--- Select a client ---")
        for ((index, client) in clients.withIndex()) {
            println("${index + 1}. ${client.name}")
        }
        print("Enter the client number: ")
        val num = scanner.nextInt()

        return if (num in 1..clients.size) {
            clients[num - 1]
        } else {
            println("Invalid client number.")
            null
        }
    }

    private fun depositToClient() {
        val client = selectClient()
        if (client != null) {
            print("Enter the deposit amount: ")
            val amount = scanner.nextDouble()
            client.deposit(amount)
        }
    }

    private fun withdrawFromClient() {
        val client = selectClient()
        if (client != null) {
            print("Enter the withdrawal amount: ")
            val amount = scanner.nextDouble()
            client.withdraw(amount)
        }
    }

    private fun showClientInfo() {
        val client = selectClient()
        client?.printInfo()
    }

    private fun showAllClients() {
        if (clients.isEmpty()) {
            println("No clients to display.")
            return
        }
        println("\n--- Clients and Balances ---")
        for (client in clients) {
            client.printInfo()
        }
    }

    private fun showTotalBalance() {
        val total = clients.sumOf { it.getBalance() }
        println("\nTotal amount of money in the bank: $$total")
    }

    private fun addClient() {
        print("\nEnter the new client's name: ")
        val name = scanner.nextLine().trim()
        if (name.isNotEmpty()) {
            print("Enter the initial deposit amount: ")
            val initialAmount = scanner.nextDouble()
            scanner.nextLine() // clear buffer

            val newClient = Client(name)
            if (initialAmount > 0) {
                newClient.deposit(initialAmount)
            }
            clients.add(newClient)
            println("Client \"$name\" added successfully.")
        } else {
            println("The name cannot be empty.")
        }
    }
}
