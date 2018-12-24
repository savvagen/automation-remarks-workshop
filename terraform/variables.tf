
# Set the variable value in *.tfvars file
# or using -var="do_token=..." CLI option
variable "do_token" {
  description = "Digital Ocean Toke (do not show in production!)"
  default = "8f2b2dabd8b817d6276608c0360635b8ff1d14fd335ae12e9aad6b65e2b98a36"
}

variable "ssh_key" {
  description = "The fingerprint of your ssh"
  default = "23624907"
}