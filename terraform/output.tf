output "instance_public_ip" {
  value = aws_instance.finance_instance.public_ip
}

output "vpc_id" {
  value = aws_vpc.finance_vpc.id
}
