#@author Rahul Mehta
#@version November 13, 2017
#The program prints out the number from 1,10.. basically emulating a while loop

.data
	msg: .asciiz "\n"
.text
.globl main

main:
	li $t0, 1 #setting a counter
loop:
	bgt $t0, 10, label #creating the conditional for the while loop
	la $a0, msg
	li $v0, 4
	syscall
	move $a0, $t0 #moving the counter to the $a0 for printing
	li $v0, 1 #printing the counter
	syscall
	addu $t0, $t0, 1 # x = x+1
	j loop #jumping back up the loop


label:
	li $v0, 10 #termination of program
	syscall
	
	