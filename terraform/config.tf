
# Configure the DigitalOcean Provider

provider "digitalocean" {
  token = "${var.do_token}"
}


resource "digitalocean_firewall" "default" {
  name = "selenoid-firewall"

  droplet_ids = ["${digitalocean_droplet.selenoid.id}"]
  tags = ["selenoid"]

  inbound_rule = [
    {
      # Allow all ports and hosts
      protocol           = "tcp",
      port_range         = "1-65535"
      source_addresses   = ["0.0.0.0/0", "::/0"]
    },
    {
      protocol           = "udp"
      port_range         = "1-65535"
      source_addresses   = ["0.0.0.0/0", "::/0"]
    },
    {
      protocol           = "icmp"
      port_range         = "1-65535"
      source_addresses   = ["0.0.0.0/0", "::/0"]
    },
    {
      protocol           = "tcp"
      port_range         = "4444"
      source_addresses   = ["192.168.1.0/24", "10.200.1.0/24",  "2002:1:2::/48"]
    },
    {
      protocol           = "tcp"
      port_range         = "8000"
      source_addresses   = ["192.168.1.0/24", "10.200.1.0/24",  "2002:1:2::/48"]
    },
    {
      protocol           = "tcp"
      port_range         = "8001"
      source_addresses   = ["192.168.1.0/24", "10.200.1.0/24",  "2002:1:2::/48"]
    },
    {
      protocol           = "tcp"
      port_range         = "4444"
      source_addresses   = ["0.0.0.0/0", "::/0"]
    },
    {
      protocol           = "tcp"
      port_range         = "80"
      source_addresses   = ["0.0.0.0/0", "::/0"]
    },
    {
      protocol           = "tcp"
      port_range         = "443"
      source_addresses   = ["0.0.0.0/0", "::/0"]
    }
  ]

  outbound_rule = [
    {
      protocol                = "tcp"
      port_range              = "1-65535"
      destination_addresses   = ["0.0.0.0/0", "::/0"]
    },
    {
      protocol                = "udp"
      port_range              = "1-65535"
      destination_addresses   = ["0.0.0.0/0", "::/0"]
    },
    {
      protocol                = "icmp"
      port_range              = "1-65535"
      destination_addresses   = ["0.0.0.0/0", "::/0"]
    }
  ]
}

# Create a new SSH key
resource "digitalocean_ssh_key" "default" {
  name       = "selenoid"
  public_key = "${file("/home/savva/.ssh/do_rsa2.pub")}"
}


# Create a do instance with docker installation
resource "digitalocean_droplet" "selenoid" {
  # ...
  image = "ubuntu-18-04-x64"
  name = "do-selenoid-hub"
  region = "lon1"
  size = "s-2vcpu-4gb"
  tags = ["selenoid"]

  #  A list of SSH IDs or fingerprints to enable in the format [12345, 123456]
  ssh_keys = [
    "${var.ssh_key}",
    "${digitalocean_ssh_key.default.fingerprint}"
  ]

  connection {
    user = "root"
    type = "ssh"
    private_key = "${file("/home/savva/.ssh/do_rsa2")}"
    timeout = "2m"

  }

  provisioner "remote-exec" {
    inline = [
      "export PATH=$PATH:/usr/bin",
      "sudo apt-get update",
      "sudo apt-get -y install python",
      #"sudo apt-get -y install nginx",
      "sudo apt-get remove docker docker-engine docker.io",
      "sudo apt-get update",
      "sudo apt-get -y install apt-transport-https ca-certificates curl software-properties-common",
      "curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -",
      "sudo apt-key fingerprint 0EBFCD88",
      "sudo add-apt-repository 'deb [arch=amd64] https://download.docker.com/linux/ubuntu xenial stable'",
      "sudo apt-get update",
      "sudo apt-get -y install docker-ce",
      "sudo docker run hello-world",
      "curl -s https://aerokube.com/cm/bash | bash && ./cm selenoid start --vnc --tmpfs 128",
      "docker run -d --name selenoid-ui --link selenoid -p 8080:8080 aerokube/selenoid-ui --selenoid-uri=http://selenoid:4444"
    ]
  }

}