# How to Run Java Project in Terminal - Step by Step

## 🎯 Complete Guide to Run Java Spring Boot Project

Since Maven is not installed, follow these steps:

---

## Step 1: Install Maven (No Password Required)

Copy and paste these commands **one by one** in your terminal:

```bash
# Create Maven directory in your home folder
mkdir -p ~/maven
cd ~/maven

# Download Maven
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz

# Extract Maven
tar -xzf apache-maven-3.9.6-bin.tar.gz

# Add Maven to your PATH (so you can use 'mvn' command)
echo 'export PATH=$PATH:$HOME/maven/apache-maven-3.9.6/bin' >> ~/.zshrc
source ~/.zshrc

# Verify Maven is installed
mvn -version
```

**Expected Output:**
```
Apache Maven 3.9.6
Maven home: /Users/komalkhanuja/maven/apache-maven-3.9.6
Java version: 24.0.2
```

---

## Step 2: Navigate to Java Backend Directory

```bash
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
```

---

## Step 3: Run the Spring Boot Application

```bash
mvn spring-boot:run
```

**What happens:**
1. **First Time:** Maven downloads all dependencies (5-10 minutes)
   - You'll see lots of download messages
   - This is normal and only happens once
   
2. **Compilation:** Java code is compiled
   - You'll see "BUILD SUCCESS" when done

3. **Server Starts:** Application starts on port 5501
   - Look for: `Started ProductivityApplication`
   - Database is initialized automatically

---

## Step 4: Access the Application

Once you see `Started ProductivityApplication`, open your browser:

**🌐 http://localhost:5501**

The application will be ready!

---

## 🛑 Stop the Server

Press `Ctrl + C` in the terminal to stop the server.

---

## 📋 Complete Command Sequence (Copy-Paste All)

If you want to do everything at once, here's the complete sequence:

```bash
# Install Maven
mkdir -p ~/maven && cd ~/maven
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
tar -xzf apache-maven-3.9.6-bin.tar.gz
echo 'export PATH=$PATH:$HOME/maven/apache-maven-3.9.6/bin' >> ~/.zshrc
source ~/.zshrc

# Verify Maven
mvn -version

# Navigate to project and run
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
mvn spring-boot:run
```

---

## ✅ Success Indicators

When everything works, you'll see:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.0)

... (more logs)

Started ProductivityApplication in X.XXX seconds
Sample data inserted successfully
```

Then open **http://localhost:5501** in your browser!

---

## 🐛 Troubleshooting

### "command not found: mvn"
- Make sure you ran: `source ~/.zshrc`
- Or close and reopen your terminal
- Verify: `mvn -version` should work

### "Port 5501 already in use"
```bash
# Find what's using the port
lsof -i :5501

# Kill the process (replace PID with actual number)
kill -9 PID
```

Or change port in `src/main/resources/application.properties`:
```
server.port=8080
```

### "BUILD FAILURE"
- Check internet connection (Maven needs to download dependencies)
- Try: `mvn clean install -U` to force update

### Slow First Build
- **Normal!** First build downloads ~100MB of dependencies
- Subsequent builds are much faster (seconds instead of minutes)

---

## 🚀 Quick Start (After Maven is Installed)

Once Maven is installed, you only need:

```bash
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
mvn spring-boot:run
```

That's it! 🎉

