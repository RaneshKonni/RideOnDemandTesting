# XPath Locator Reference Guide

## Document Information
- **Created**: June 25, 2026
- **Purpose**: Quick reference for all page object locators
- **Framework**: Selenium WebDriver with POM pattern
- **Total Locators**: 45+

---

## CustomerDashboardPage Locators

### Page Headers & Messages
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Welcome Message | `//h1[contains(text(), 'Welcome')]` | Verify dashboard loaded | ✅ |
| Active Req Heading | `//h2[contains(text(), 'Active Requirements')]` | Check section presence | ✅ |
| Vehicle Req Text | `//p[contains(text(), 'Need a vehicle')]` | Verify description text | ✅ |

### Navigation Buttons
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Profile Button | `//button/span[contains(@class,'avatar')]` | Navigate to profile | ✅ |
| Post Requirement Button | `//button[contains(text(), 'Post')]` | Navigate to post req | ✅ |

### Data Elements
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| My Requirements Section | `//div[contains(text(), 'My Requirements')]` | Check section | ✅ |
| Requirement Items | `//div[@class='requirement-item']` | Count requirements | ✅ |
| Active Req Count | `//span[contains(text(), 'Active Requirements')]//following::span[1]` | Get count value | ✅ |

### Locator Explanation
```
// = Any level in DOM
contains(text(), '...') = Match partial text
contains(@class, '...') = Match class attribute
//following::span[1] = Next span element
```

---

## CustomerProfilePage Locators

### Page Headers
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Profile Heading | `//h1[contains(text(), 'Profile')]` | Verify page loaded | ✅ |
| Profile & Notif Text | `//span[contains(text(), 'Profile & Notifications')]` | Check header text | ✅ |
| Logo Image | `//img[@alt='logo']` | Verify logo presence | ✅ |

### Account Details Section
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Account Details Heading | `//h2[contains(text(), 'Account Details')]` | Check section | ✅ |
| Customer Email | `//div[contains(text(), 'Email')]//following::span[1]` | Get email value | ✅ |
| Customer Mobile | `//div[contains(text(), 'Mobile')]//following::span[1]` | Get mobile value | ✅ |
| Customer City | `//div[contains(text(), 'City')]//following::span[1]` | Get city value | ✅ |

### Notifications Section
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Notifications Heading | `//h2[contains(text(), 'Requirements')]` | Check section | ✅ |
| Items Count | `//span[contains(text(), 'items')]` | Get items count | ✅ |
| Unread Count | `//span[contains(text(), 'unread')]` | Get unread count | ✅ |
| No Notifications Msg | `//p[contains(text(), 'No customer notifications')]` | Check empty state | ✅ |

### Action Buttons
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Sign Out Button | `//button[text() = 'Sign out']` | Logout user | ✅ |
| Back to Dashboard | `//button[contains(@class, 'back')]` | Navigate back | ✅ |
| Dashboard Link | `//a[contains(text(), 'Dashboard')]` | Navigate back alt | ✅ |

### Locator Techniques Used
```
@alt='logo' = Match alt attribute value (exact match)
text() = 'Sign out' = Match exact text only
contains(@class, 'back') = Match class containing value
//following::span[1] = Get next sibling span
```

---

## CustomerPostRequirementPage Locators

### Page Structure
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Page Heading | `//h1[contains(text(), 'Post Requirement')]` | Verify page | ✅ |
| Nav Text | `//span[contains(text(), 'Post Requirement')]` | Check nav section | ✅ |
| Create Req Text | `//h2[contains(text(), 'Create your rental requirement')]` | Check message | ✅ |
| Step Indicator | `//div[@class='step-indicator']` | Find step tracker | ✅ |
| Step Numbers | `//span[@class='step-number']` | Get step list | ✅ |

### Step 1: Basic Details
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Start Date Input | `//input[@name='startDate']` | Enter start date | ✅ |
| Start Date Picker | `//input[@type='date'][@name='startDate']` | Click date picker | ✅ |
| End Date Input | `//input[@name='endDate']` | Enter end date | ✅ |
| End Date Picker | `//input[@type='date'][@name='endDate']` | Click date picker | ✅ |
| Vehicle Dropdown | `//select[@name='vehicleType']` | Select vehicle | ✅ |
| Bike Option | `//option[contains(text(), 'Bike')]` | Bike choice | ✅ |
| Scooty Option | `//option[contains(text(), 'Scooty')]` | Scooty choice | ✅ |
| Car Option | `//option[contains(text(), 'Car')]` | Car choice | ✅ |
| SUV Option | `//option[contains(text(), 'SUV')]` | SUV choice | ✅ |

### Step 2: Location & Budget
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Pickup Location | `//input[@name='pickupLocation']` | Enter location | ✅ |
| Budget Per Day | `//input[@name='budget']` | Enter budget | ✅ |

### Step 3: Additional Details
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Additional Details | `//textarea[@name='additionalDetails']` | Enter details | ✅ |
| Preferred Shops | `//input[@name='preferredShops']` | Enter shops | ✅ |

### Navigation Buttons
| Element | XPath | Usage | Status |
|---------|-------|-------|--------|
| Next Button | `//button[text()='Next']` | Go to next step | ✅ |
| Back Button | `//button[text()='Back']` | Go to previous step | ✅ |
| Post Requirement | `//button[contains(text(), 'Post Requirement')]` | Submit form | ✅ |
| Back to Profile | `//button[contains(text(), 'Profile')]` | Exit to profile | ✅ |
| Profile Link | `//a[contains(text(), 'Profile')]` | Exit alt | ✅ |

### Locator Patterns
```
@name='startDate' = Match input by name attribute
[@type='date'] = Filter by input type
@name='vehicleType' = Select dropdown by name
/option[contains(text(), 'Bike')] = Option within select
@class='step-number' = Match by class exactly
text()='Next' = Match exact button text
```

---

## Locator Strategy & Best Practices

### 1. Text-Based Locators
```xpath
// Match exact text
//button[text()='Sign out']

// Match partial text (recommended)
//h1[contains(text(), 'Welcome')]

// Normalize spaces
//button[normalize-space()='Sign out']
```

### 2. Attribute-Based Locators
```xpath
// Match exact attribute value
//img[@alt='logo']

// Match attribute containing value
//input[@name='startDate']
//button[contains(@class, 'back')]

// Match multiple attributes
//input[@type='date'][@name='startDate']
```

### 3. Hierarchical Locators
```xpath
// Parent-child relationship
//div[@class='step-indicator']

// Sibling relationship
//div[contains(text(), 'Email')]//following::span[1]

// Ancestor relationship
//button/span[contains(@class,'avatar')]
```

### 4. Combined Expressions
```xpath
// Multiple conditions
//input[@type='date'][@name='endDate']

// Starts with
//span[starts-with(text(), 'Active')]

// Ends with
//span[ends-with(text(), 'Requirements')]
```

---

## Locator Maintenance Guide

### When to Update Locators

#### Update Required When:
- ❌ Element class changes significantly
- ❌ Element IDs are modified
- ❌ Text content changes
- ❌ DOM hierarchy restructured
- ❌ Test fails with "Element not found"

#### Check Before Updating:
1. Inspect element in browser (F12)
2. Verify current XPath works
3. Test updated XPath in console
4. Run test to confirm fix

### Locator Validation Steps
```javascript
// In browser console to test XPath:
$x("//button[text()='Sign out']")  // Should return element

// If empty array [], XPath is incorrect
// If single element, XPath is correct
```

### Common Issues & Solutions

#### Issue 1: Case Sensitivity
```xpath
// WRONG (case-sensitive)
//button[text()='sign out']

// RIGHT (case must match)
//button[text()='Sign out']

// SOLUTION: Use contains() for case handling
//button[contains(text(), 'sign')]
```

#### Issue 2: Partial Text Match
```xpath
// WRONG (exact match fails if text varies)
//button[text()='Next Step']

// RIGHT (flexible partial match)
//button[contains(text(), 'Next')]
```

#### Issue 3: Leading/Trailing Spaces
```xpath
// WRONG (spaces break match)
//span[text()=' Active ']

// RIGHT (normalize spaces)
//span[normalize-space()='Active']
```

#### Issue 4: Multiple Elements
```xpath
// WRONG (ambiguous - returns first match)
//button

// RIGHT (specific to button's purpose)
//button[contains(text(), 'Sign out')]

// BETTER (specify position if needed)
//button[contains(text(), 'Sign out')][1]
```

---

## Page Object Locator Organization

### CustomerDashboardPage
```
Total Locators: 10
- Page Headers: 3
- Buttons: 2
- Data Elements: 5
```

### CustomerProfilePage
```
Total Locators: 12
- Headers: 3
- Account Section: 4
- Notifications: 4
- Buttons: 1
```

### CustomerPostRequirementPage
```
Total Locators: 25
- Page Structure: 5
- Step 1 Fields: 10
- Step 2 Fields: 2
- Step 3 Fields: 2
- Buttons: 5
- Navigation: 1
```

---

## Tips for Creating New Locators

### 1. Start Simple
```xpath
// First try ID (if available)
//*[@id='mybutton']

// Then try name attribute
//input[@name='username']

// Then try class
//*[@class='primary-btn']
```

### 2. Fallback to Text & Hierarchy
```xpath
// Use text content
//button[contains(text(), 'Login')]

// Or use hierarchy
//div[@class='form']//button[1]
```

### 3. Verify Uniqueness
```xpath
// Check if your XPath returns only one element:
// Should return [1 element] in console, not 0 or multiple
```

### 4. Use Wait with Locators
```java
// Always pair with explicit wait
wait.until(ExpectedConditions.visibilityOf(element));
```

---

## XPath Tools & Resources

### Browser Console Testing
```javascript
// Firefox/Chrome DevTools Console:
$x("//button[text()='Login']")

// Returns array of matching elements
// Empty array = XPath not found
// Array with elements = Success
```

### XPath Checkers
- Chropath (Chrome Extension)
- XPath Finder (Firefox Extension)
- DevTools Elements Tab: Right-click → Copy → Copy XPath

### Regex in XPath
```xpath
// Match patterns
//input[matches(@id, 'user_.*')]

// Contains any case
//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'login')]
```

---

## Locator Performance Considerations

### Fast Locators (Recommended)
```xpath
✅ //input[@id='username']  // Direct attribute
✅ //button[@name='submit']  // Name-based
✅ //span[text()='Welcome']  // Text-based
```

### Slow Locators (Avoid)
```xpath
❌ //div/div/div/button  // Deep hierarchy
❌ //*[contains(@*, '*')]  // Wildcard matching
❌ //div[contains(., 'text')]  // Descendant search
```

### Balanced Locators (Good)
```xpath
✅ //div[@class='form']//button[contains(text(), 'Login')]
✅ //input[@name='startDate']
✅ //span[contains(@class, 'badge')]
```

---

## Locator Refactoring Checklist

When maintaining or updating locators:

- ☐ Is locator unique (returns 1 element only)?
- ☐ Does locator work in current version?
- ☐ Is locator maintainable (avoid deep hierarchy)?
- ☐ Does locator handle text variations?
- ☐ Is locator performance optimized?
- ☐ Are spaces/case handled correctly?
- ☐ Is locator documented in code?
- ☐ Have tests passed with new locator?

---

## Quick XPath Reference Table

| Syntax | Meaning | Example |
|--------|---------|---------|
| `//` | Any level | `//button` |
| `@` | Attribute | `@id='save'` |
| `[1]` | First element | `//button[1]` |
| `text()` | Text content | `text()='Save'` |
| `contains()` | Partial match | `contains(text(), 'Sav')` |
| `starts-with()` | Beginning match | `starts-with(@id, 'btn')` |
| `ends-with()` | End match | `ends-with(@class, 'active')` |
| `and` | AND condition | `[@type='text' and @name='username']` |
| `or` | OR condition | `[text()='A' or text()='B']` |
| `//following::` | Following sibling | `//label//following::input[1]` |
| `//preceding::` | Previous sibling | `//input//preceding::label[1]` |
| `/parent::` | Parent element | `/parent::div` |
| `[not(...)]` | Negation | `[not(@disabled)]` |
| `normalize-space()` | Remove spaces | `normalize-space()='Save'` |
| `translate()` | Character replace | `translate(text(), 'ABC', 'abc')` |

---

## Conclusion

This guide provides:
✅ Reference for all 45+ locators
✅ Techniques for creating new locators
✅ Best practices and patterns
✅ Troubleshooting guidelines
✅ Performance considerations
✅ Maintenance procedures

Use this as your go-to reference when:
- Creating new test cases
- Updating existing locators
- Debugging test failures
- Optimizing test performance

---

**For questions about locators, refer to this guide or review the page object source code.**

---

**Happy Locating! 🎯**

