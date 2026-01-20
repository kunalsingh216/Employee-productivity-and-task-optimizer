# Install Maven Without Sudo (No Password Required)

## Method: Install Maven in Your Home Directory

### Step 1: Cancel Current Installation
Press `Ctrl + C` to cancel the Homebrew installation.

### Step 2: Create Maven Directory in Home Folder
```bash
mkdir -p ~/maven
cd ~/maven
```

### Step 3: Download Maven
```bash
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
```

### Step 4: Extract Maven
```bash
tar -xzf apache-maven-3.9.6-bin.tar.gz
```

### Step 5: Add to PATH (No Sudo Needed)
```bash
echo 'export PATH=$PATH:$HOME/maven/apache-maven-3.9.6/bin' >> ~/.zshrc
source ~/.zshrc
```

### Step 6: Verify Maven
```bash
mvn -version
```

### Step 7: Run Your Project
```bash
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
mvn spring-boot:run
```

---

## Complete Commands (Copy-Paste)

```bash
# Cancel current installation first (Ctrl+C)

# Setup Maven in home directory
mkdir -p ~/maven && cd ~/maven
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
tar -xzf apache-maven-3.9.6-bin.tar.gz
echo 'export PATH=$PATH:$HOME/maven/apache-maven-3.9.6/bin' >> ~/.zshrc
source ~/.zshrc

# Verify
mvn -version

# Run project
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
mvn spring-boot:run
```

This method installs Maven in your home directory and doesn't require any password!

