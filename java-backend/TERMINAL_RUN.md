# Run Project in Terminal - Step by Step

## Quick Method: Install Maven via Homebrew (Easiest)

### Step 1: Install Homebrew (if not installed)
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Follow the on-screen instructions. You may need to add Homebrew to your PATH.

### Step 2: Install Maven
```bash
brew install maven
```

### Step 3: Verify Maven
```bash
mvn -version
```

### Step 4: Navigate to Project and Run
```bash
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
mvn spring-boot:run
```

---

## Alternative Method: Manual Maven Installation

### Step 1: Download Maven
```bash
cd ~/Downloads
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
```

### Step 2: Extract Maven
```bash
tar -xzf apache-maven-3.9.6-bin.tar.gz
sudo mv apache-maven-3.9.6 /usr/local/maven
```

### Step 3: Add Maven to PATH
```bash
echo 'export PATH=$PATH:/usr/local/maven/bin' >> ~/.zshrc
source ~/.zshrc
```

### Step 4: Verify and Run
```bash
mvn -version
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
mvn spring-boot:run
```

---

## What Happens When You Run

1. **First Time**: Maven downloads dependencies (5-10 minutes)
   - You'll see lots of download messages
   - This only happens once

2. **Compilation**: Java code is compiled
   - You'll see "BUILD SUCCESS" when done

3. **Server Starts**: Application starts on port 5501
   - Look for: "Started ProductivityApplication"
   - Database is initialized automatically

4. **Access**: Open browser to http://localhost:5501

---

## Complete Terminal Commands (Copy-Paste Ready)

```bash
# Install Homebrew (if needed)
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install Maven
brew install maven

# Verify
mvn -version

# Navigate to project
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend

# Run the application
mvn spring-boot:run
```

---

## Stop the Server

Press `Ctrl + C` in the terminal to stop the server.

---

## Troubleshooting

### "command not found: mvn"
- Maven is not installed or not in PATH
- Run: `source ~/.zshrc` after adding Maven to PATH
- Or reinstall Maven

### "Port 5501 already in use"
- Another application is using the port
- Stop it or change port in `application.properties`

### "BUILD FAILURE"
- Check internet connection (Maven needs to download dependencies)
- Try: `mvn clean install -U` to force update

### Slow First Build
- Normal! First build downloads ~100MB of dependencies
- Subsequent builds are much faster

