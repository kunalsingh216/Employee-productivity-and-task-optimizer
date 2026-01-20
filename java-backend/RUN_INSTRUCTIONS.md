# How to Run the Java Project

## Prerequisites Check ✅
- ✅ Java is installed (version 24.0.2 detected)

## Step 1: Install Maven

You have two options:

### Option A: Install Maven using Homebrew (Recommended for macOS)

```bash
# Install Homebrew if you don't have it
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install Maven
brew install maven
```

### Option B: Install Maven Manually

1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract the archive
3. Add to your PATH:
   ```bash
   export PATH=$PATH:/path/to/maven/bin
   ```

### Verify Maven Installation

```bash
mvn -version
```

You should see Maven version information.

## Step 2: Run the Project

Once Maven is installed, navigate to the project directory and run:

```bash
cd java-backend
mvn spring-boot:run
```

The application will:
- Download all dependencies (first time only, may take a few minutes)
- Compile the code
- Start the server on port 5501
- Initialize the database with sample data

## Step 3: Access the Application

Once you see "Started ProductivityApplication" in the console:

- **Frontend**: Open http://localhost:5501 in your browser
- **API**: http://localhost:5501/api

## Alternative: Using an IDE

If you prefer using an IDE:

### IntelliJ IDEA
1. Open IntelliJ IDEA
2. File → Open → Select the `java-backend` folder
3. Wait for Maven to sync dependencies
4. Right-click on `ProductivityApplication.java`
5. Select "Run 'ProductivityApplication'"

### VS Code
1. Install "Extension Pack for Java" extension
2. Open the `java-backend` folder
3. Open `ProductivityApplication.java`
4. Click "Run" button above the main method

### Eclipse
1. File → Import → Maven → Existing Maven Projects
2. Select the `java-backend` folder
3. Right-click project → Run As → Spring Boot App

## Troubleshooting

### Port 5501 Already in Use
If you get a port conflict error:
1. Stop the other application using port 5501, OR
2. Edit `src/main/resources/application.properties`
3. Change `server.port=5501` to another port (e.g., `server.port=8080`)

### Maven Download Issues
If Maven downloads are slow:
- Check your internet connection
- First build may take 5-10 minutes to download all dependencies
- Subsequent builds will be much faster

### Database Issues
If the database doesn't initialize:
- Delete `productivity_tool.db` file (if it exists)
- Restart the application
- The database will be recreated automatically

## Quick Test

After starting the server, test the API:

```bash
# Test employees endpoint
curl http://localhost:5501/api/employees

# Test tasks endpoint
curl http://localhost:5501/api/tasks

# Test dashboard
curl http://localhost:5501/api/analytics/dashboard
```

## Expected Output

When the server starts successfully, you should see:

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

Then open your browser to http://localhost:5501 to see the application!


