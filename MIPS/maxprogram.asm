#This program asks the user for two numbers and then finds the max
#@author Rahul Mehta
#@Version November 13, 2017

.data
	prompt: 
		.asciiz "Input number" #setting the prompt to a ascii string

.text
.globl main

main:
	la $a0, prompt #loading the address of the string into $a0
	li $v0, 4 #getting it ready to print
	syscall
	li $v0, 5 #reading the input from the console
	syscall
	move $t1, $v0 #moving to temporary register
	li $v0, 4
	syscall
	li $v0, 5
	syscall
	move $t2, $v0
	bgt $t1, $t2, label #creating a if, then
	move $a0, $t2 #moving the bigger number to the $a0
	li $v0, 1 #getting it ready to print it out
	syscall
	li $v0, 10 #terminating the program
	syscall
label: 
	move $a0, $t1 #moving the bigger number to $a0
	li $v0, 1 #getting it ready to print
	syscall
	li $v0, 10 #termination
	syscall

	
	
	