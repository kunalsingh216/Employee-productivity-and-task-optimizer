# How to Run the Project in Terminal

You have **two options** to run this project. Choose the one that works best for you!

---

## 🚀 Option 1: Node.js Version (QUICKEST - Recommended)

Since you already have Node.js installed, this is the fastest way to run the project.

### Step 1: Navigate to Project Directory
```bash
cd /Users/komalkhanuja/Downloads/employee-productivity-tool
```

### Step 2: Install Dependencies (First Time Only)
```bash
npm install
```

This will install:
- express
- sqlite3
- cors

### Step 3: Run the Server
```bash
npm start
```

Or directly:
```bash
node server.js
```

### Step 4: Access the Application
- Open your browser: **http://localhost:5501**
- The application will be ready!

### Stop the Server
Press `Ctrl + C` in the terminal

---

## ☕ Option 2: Java Spring Boot Version

### Prerequisites
- Java 17+ ✅ (You have Java 24.0.2)
- Maven (needs to be installed)

### Step 1: Install Maven (If Not Installed)

**Quick Install (No Password Required):**
```bash
# Create directory and download Maven
mkdir -p ~/maven
cd ~/maven
curl -O https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz

# Extract Maven
tar -xzf apache-maven-3.9.6-bin.tar.gz

# Add Maven to PATH
echo 'export PATH=$PATH:$HOME/maven/apache-maven-3.9.6/bin' >> ~/.zshrc
source ~/.zshrc

# Verify
mvn -version
```

### Step 2: Navigate to Java Backend
```bash
cd /Users/komalkhanuja/Downloads/employee-productivity-tool/java-backend
```

### Step 3: Run the Application
```bash
mvn spring-boot:run
```

**First time:** Maven will download dependencies (5-10 minutes)
**Subsequent runs:** Much faster!

### Step 4: Access the Application
- Open your browser: **http://localhost:5501**
- The application will be ready!

### Stop the Server
Press `Ctrl + C` in the terminal

---

## 📋 Quick Comparison

| Feature | Node.js Version | Java Version |
|---------|----------------|--------------|
| **Setup Time** | ~30 seconds | ~10 minutes (first time) |
| **Dependencies** | npm install | Maven downloads |
| **Port** | 5501 | 5501 |
| **Database** | SQLite (same) | SQLite (same) |
| **Performance** | Fast | Fast |
| **Best For** | Quick testing | Production/Enterprise |

---

## 🎯 Recommended: Start with Node.js Version

Since you already have Node.js, use this command sequence:

```bash
cd /Users/komalkhanuja/Downloads/employee-productivity-tool
npm install
npm start
```

Then open **http://localhost:5501** in your browser!

---

## 🔍 Verify Installation

### Check Node.js:
```bash
node --version
# Should show: v22.18.0 or similar
```

### Check npm:
```bash
npm --version
```

### Check Java (for Java version):
```bash
java -version
# Should show: Java 24.0.2
```

### Check Maven (for Java version):
```bash
mvn -version
# Should show Maven version if installed
```

---

## 🐛 Troubleshooting

### Port 5501 Already in Use
```bash
# Find what's using the port
lsof -i :5501

# Kill the process (replace PID with actual process ID)
kill -9 PID
```

Or change the port in:
- **Node.js:** Edit `server.js` line 570: `const port = process.env.PORT || 5501;`
- **Java:** Edit `java-backend/src/main/resources/application.properties`: `server.port=5501`

### Node.js Dependencies Not Installing
```bash
# Clear cache and reinstall
rm -rf node_modules package-lock.json
npm install
```

### Maven Not Found
Follow the Maven installation steps above, or use the Node.js version instead.

---

## ✅ Success Indicators

When the server starts successfully, you'll see:

**Node.js:**
```
Server running at http://localhost:5501
Database initialized with sample data
```

**Java:**
```
Started ProductivityApplication in X.XXX seconds
Sample data inserted successfully
```

Then open **http://localhost:5501** in your browser to see the application!

