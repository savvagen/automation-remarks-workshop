### Ansible instructions

#### Commands


1. Run ansible commande from /etc/ansible/hosts file
  
  `ansible test -m ping`

2. Run Ansible with hosts file:
  
  `ansible example -i hosts -m ping`
  
3. Run Ansible with hosts file with logs:
    
    `ansible example -i hosts -m ping -vvvv`
    
4. Run ansible with shell command or install library:

    `ansible example -m shell -a "apt install nginx"`
    
    or 
    
    `ansible example -m apt -name=nginx`
    
 
    
   
 