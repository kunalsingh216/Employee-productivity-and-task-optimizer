# Test Cases Screenshots Guide

This document outlines which screenshots you should capture for documenting test cases in the Employee Productivity Tool.

## 📸 Screenshot Categories

### 1. **Dashboard View** (Main Page)
**Screenshot Name:** `01_dashboard_overview.png`
- **What to capture:**
  - Full dashboard with all stat cards (Total Tasks, Completed, Active, Time Accuracy)
  - Quick Actions section
  - Top Performers section
  - All tabs visible (Dashboard, Tasks, Employees, Analytics)

**Test Cases:**
- ✅ Dashboard loads with correct statistics
- ✅ Stat cards display accurate numbers
- ✅ Top performers list shows correctly
- ✅ Navigation tabs are functional

---

### 2. **Tasks - List View**
**Screenshot Name:** `02_tasks_list_view.png`
- **What to capture:**
  - Task list table with all columns (Title, Difficulty, Priority, Owner, Status, Actions)
  - Multiple tasks visible
  - Status badges (pending, in_progress, completed)
  - Priority labels
  - Filter dropdown and search box

**Test Cases:**
- ✅ All tasks are displayed
- ✅ Tasks can be filtered by status
- ✅ Search functionality works
- ✅ Task details are correct

---

### 3. **Tasks - Board/Kanban View**
**Screenshot Name:** `03_tasks_kanban_view.png`
- **What to capture:**
  - Kanban board with 4 columns (Backlog, In Progress, Completed, Blocked/Cancelled)
  - Task cards in different columns
  - Task counts per column
  - Action buttons on cards

**Test Cases:**
- ✅ View toggle works (List ↔ Board)
- ✅ Tasks appear in correct columns
- ✅ Task counts are accurate
- ✅ Drag/drop or status change works

---

### 4. **Create New Task**
**Screenshot Name:** `04_create_task_form.png`
- **What to capture:**
  - Create Task form with all fields:
    - Title input
    - Description textarea
    - Difficulty (1-10)
    - Estimated Hours
    - Priority dropdown
  - "Create Task" button
  - Form validation (if showing errors)

**Test Cases:**
- ✅ Form accepts valid input
- ✅ Required fields validation
- ✅ Task is created successfully
- ✅ Success toast notification appears

---

### 5. **Task Status Update**
**Screenshot Name:** `05_task_status_update.png`
- **What to capture:**
  - Task with status dropdown
  - Status change to "in_progress" or "completed"
  - If completed: actual hours input prompt
  - Updated task in the list/board

**Test Cases:**
- ✅ Status can be changed
- ✅ Actual hours prompt appears for completion
- ✅ Task moves to correct column/status
- ✅ Database updates correctly

---

### 6. **Auto-Assign Task**
**Screenshot Name:** `06_auto_assign_task.png`
- **What to capture:**
  - Unassigned task
  - "Auto Assign" button click
  - Success message showing assigned employee
  - Task now showing assigned employee name

**Test Cases:**
- ✅ Auto-assign button works
- ✅ Best employee is selected algorithmically
- ✅ Assignment score is calculated
- ✅ Task shows assigned employee

---

### 7. **Employees List**
**Screenshot Name:** `07_employees_list.png`
- **What to capture:**
  - Employee table with columns:
    - Name & Email
    - Efficiency Score
    - Workload bar (visual)
    - Completed Tasks count
    - Actions (Recommendations, Remove)
  - Search box
  - "ADD EMPLOYEE" button

**Test Cases:**
- ✅ All employees are listed
- ✅ Workload bars display correctly
- ✅ Efficiency scores are shown
- ✅ Search filters employees

---

### 8. **Add New Employee**
**Screenshot Name:** `08_add_employee_modal.png`
- **What to capture:**
  - Modal dialog with form:
    - Name input
    - Email input
    - Experience Level (1-5)
    - Efficiency Score (1-10)
  - Save button
  - Form validation (if any)

**Test Cases:**
- ✅ Modal opens correctly
- ✅ Form accepts valid data
- ✅ Email uniqueness validation
- ✅ Employee is created successfully

---

### 9. **Employee Recommendations**
**Screenshot Name:** `09_employee_recommendations.png`
- **What to capture:**
  - Recommendations modal
  - Employee details (name, workload, efficiency)
  - List of recommendations with priority badges:
    - High priority (red/orange)
    - Medium priority (yellow)
    - Low priority (blue/green)
  - Recommendation messages

**Test Cases:**
- ✅ Recommendations button opens modal
- ✅ Recommendations are relevant
- ✅ Priority badges display correctly
- ✅ Data is accurate

---

### 10. **Analytics Dashboard**
**Screenshot Name:** `10_analytics_dashboard.png`
- **What to capture:**
  - Analytics page with:
    - Stat cards (Total, Completed, In Progress, Time Accuracy)
    - Productivity Trends chart (line graph)
    - Difficulty Distribution chart (bar graph)
    - Priority Distribution chart (doughnut chart)
    - Top Performers section
  - Auto-refresh toggle
  - Refresh button

**Test Cases:**
- ✅ All charts render correctly
- ✅ Data is accurate
- ✅ Auto-refresh works
- ✅ Manual refresh works

---

### 11. **Analytics - Charts Detail**
**Screenshot Name:** `11_analytics_charts.png`
- **What to capture:**
  - Close-up of all three charts:
    - Line chart (Productivity Trends)
    - Bar chart (Difficulty Mix)
    - Doughnut chart (Priority Mix)
  - Chart legends
  - Data points visible

**Test Cases:**
- ✅ Charts display data correctly
- ✅ Legends are accurate
- ✅ Chart interactions work (hover, etc.)

---

### 12. **Error Scenarios**
**Screenshot Name:** `12_error_scenarios.png`
- **What to capture:**
  - Error toast notifications:
    - "Task not found" error
    - "Email already exists" error
    - "No suitable employee found" error
    - Validation errors
  - Empty state messages
  - Loading spinners

**Test Cases:**
- ✅ Error messages display correctly
- ✅ Toast notifications appear
- ✅ Empty states show when no data
- ✅ Loading states work

---

### 13. **Mobile/Responsive View** (Optional)
**Screenshot Name:** `13_mobile_responsive.png`
- **What to capture:**
  - Application in mobile view (narrow browser window)
  - Responsive layout changes
  - Stacked cards/columns
  - Mobile-friendly navigation

**Test Cases:**
- ✅ Layout adapts to screen size
- ✅ All features accessible on mobile
- ✅ Touch interactions work

---

### 14. **Database Initialization**
**Screenshot Name:** `14_database_initialization.png`
- **What to capture:**
  - Terminal/console showing:
    - "Sample data inserted successfully"
    - Spring Boot startup logs
    - Database connection messages

**Test Cases:**
- ✅ Database initializes correctly
- ✅ Sample data is inserted
- ✅ No errors during startup

---

### 15. **API Testing (Postman/curl)**
**Screenshot Name:** `15_api_testing.png`
- **What to capture:**
  - Postman or terminal showing:
    - API request (GET /api/employees)
    - API response (JSON data)
    - Status code (200 OK)
  - Multiple endpoints tested

**Test Cases:**
- ✅ API endpoints return correct data
- ✅ Status codes are correct
- ✅ JSON structure is valid
- ✅ CORS headers are present

---

## 📋 Screenshot Checklist

Before submitting test cases, ensure you have:

- [ ] Dashboard overview
- [ ] Tasks list view
- [ ] Tasks board/kanban view
- [ ] Create task form
- [ ] Task status update
- [ ] Auto-assign functionality
- [ ] Employees list
- [ ] Add employee modal
- [ ] Employee recommendations
- [ ] Analytics dashboard
- [ ] Charts detail view
- [ ] Error scenarios
- [ ] API testing results
- [ ] Database initialization

---

## 🎯 Best Practices for Screenshots

1. **Use Clear Names:** Name files descriptively (e.g., `01_dashboard_overview.png`)
2. **Full Screen Capture:** Capture entire browser window when possible
3. **Highlight Important Elements:** Use arrows or annotations for key features
4. **Before/After:** Show state changes (e.g., before and after task assignment)
5. **Include URL:** Show browser address bar to confirm correct page
6. **Data Visibility:** Ensure test data is clearly visible
7. **Error States:** Capture error messages and validation
8. **Responsive:** Test on different screen sizes

---

## 📝 Test Case Documentation Template

For each screenshot, document:

```
**Test Case ID:** TC-001
**Test Case Name:** Dashboard Loads Correctly
**Screenshot:** 01_dashboard_overview.png
**Steps:**
1. Navigate to http://localhost:5501
2. Verify dashboard loads
3. Check stat cards display
4. Verify top performers section

**Expected Result:** Dashboard displays with all statistics and data
**Actual Result:** [Match screenshot]
**Status:** ✅ Pass / ❌ Fail
```

---

## 🔧 Tools for Screenshots

- **macOS:** Cmd + Shift + 4 (select area) or Cmd + Shift + 3 (full screen)
- **Windows:** Snipping Tool or Win + Shift + S
- **Browser Extensions:** Full Page Screen Capture
- **Annotation Tools:** 
  - Skitch
  - Annotate
  - Markup tools

---

## 📁 Recommended Folder Structure

```
test-cases/
├── screenshots/
│   ├── 01_dashboard_overview.png
│   ├── 02_tasks_list_view.png
│   ├── 03_tasks_kanban_view.png
│   ├── 04_create_task_form.png
│   ├── 05_task_status_update.png
│   ├── 06_auto_assign_task.png
│   ├── 07_employees_list.png
│   ├── 08_add_employee_modal.png
│   ├── 09_employee_recommendations.png
│   ├── 10_analytics_dashboard.png
│   ├── 11_analytics_charts.png
│   ├── 12_error_scenarios.png
│   ├── 13_mobile_responsive.png
│   ├── 14_database_initialization.png
│   └── 15_api_testing.png
└── test-case-documentation.md
```

---

This guide ensures comprehensive test case documentation with visual evidence of all functionality!

