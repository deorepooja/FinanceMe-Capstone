provider "aws" {
  region = "us-east-1"
}

resource "aws_vpc" "finance_vpc" {
  cidr_block = "10.0.0.0/16"
  tags = {
    Name = "FinanceMe-VPC"
  }
}

resource "aws_subnet" "finance_subnet" {
  vpc_id            = aws_vpc.finance_vpc.id
  cidr_block        = "10.0.1.0/24"
  map_public_ip_on_launch = true
  availability_zone = "us-east-1a"

  tags = {
    Name = "FinanceMe-Subnet"
  }
}

resource "aws_security_group" "finance_sg" {
  name        = "financeme-sg"
  description = "Allow SSH and HTTP"
  vpc_id      = aws_vpc.finance_vpc.id

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "FinanceMe-SG"
  }
}

resource "aws_instance" "finance_instance" {
  ami                    = "ami-0c2b8ca1dad447f8a"  # Update this to latest Amazon Linux 2 or Ubuntu AMI
  instance_type          = "t2.micro"
  subnet_id              = aws_subnet.finance_subnet.id
  vpc_security_group_ids = [aws_security_group.finance_sg.id]
  associate_public_ip_address = true

  tags = {
    Name = "FinanceMe-EC2"
  }
}
