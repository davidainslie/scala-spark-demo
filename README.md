# Vending Machine

Build a vending machine given the provided skeleton project.
Entry point : 'VendingMachine.scala'

General:
    - free to use any libs or frameworks you like (but think production quality)
    - test framework of choice
    - code should be readable, self-explanatory
    - 4 hours to implement
    - send the result back within 4 hours to
        - 'koen.adriaenssens@centrica.com'
        - 'tom.rigole@centrica.com'

Implement 'VendingMachine' trait and dependencies:
    admin:
        - admin adds items to the machine
        - admin adds change to the machine
    customer:
        - client selects item and provides coins
        - machine delivers the item with correct change

Requirements:
    Primary
        - should always provide item with correct change or no sale.
        - simple but correct change calculation algorithm.
        - pricing service is assumed to be in-process

    Secondary
        - Pricing service becomes a remote service
            - you don't have to provide the remote service implementation.
            - the vending machine should be able to handle typical network situations:
                - potential for high latency
                - connection failures
            - client should never have to wait longer than 1 second for a response from the machine
            - machine should prefer making a suboptimal sale over making no sale at all

    Bonus
        - 2-slot machine
            - 2 clients trying to buy the same item (only one wins, correct state management)
        - optimal change calculation algo


Hints:
    - start with trivial but correct implementation then optimize/refine as you go.
    - optimize change algorithm last.