.data
	prompt: .asciiz "Input number from 1-20" #First prompt for a number
	wrongnumber: .asciiz "Wrong Number. Guess Again " #message for wrong #
	rightnumber: .asciiz "Cool you got it correct." #message for write number

.text
.globl main
main:

	li $t0, 10 #setting my number
	la $a0, prompt 
	li $v0, 4 #asking user for his first number
	syscall
	li $v0, 5
	syscall
	move $t1, $v0
loop:
	beq $t0, $t1, end #checking if its equal
	la $a0, wrongnumber #printing that its wrong
	li $v0, 4
	syscall
	la $a0, prompt #asking again
	li $v0, 4
	syscall
	li $v0, 5 #reading it
	syscall
	move $t1, $v0 #moving it back so it can check again
	j loop #jumping back again

end:
	la $a0, rightnumber #printing you got right number
	li $v0, 4 #printing it out
	syscall
	li $v0, 10 #termination
	syscall
	