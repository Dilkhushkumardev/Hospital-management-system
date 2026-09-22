# Security & Compliance Guidelines (SECURITY.md)

## 1. Authentication & Access Control
- Access to the application requires verified login credentials stored in the `login` table.
- Login passwords should be hashed (e.g., using BCrypt or SHA-256 with salt) in production deployments.
- Password inputs utilize `JPasswordField` to prevent screen snooping.

---

## 2. Database & SQL Security
- **Database Connection:** Credentials in `conn.java` should never use hardcoded production root passwords in public source control.
- **SQL Sanitization:** When refactoring database queries, use `java.sql.PreparedStatement` to prevent SQL Injection risks.
- **Principle of Least Privilege:** Create dedicated MySQL application users with restricted permissions (`SELECT, INSERT, UPDATE, DELETE`) rather than using global `root` superuser accounts.

---

## 3. Patient Data Privacy & Compliance
- **Identity Documents (Aadhar / Voter ID / License):** National identifiers must be guarded against unauthorized export or logging.
- **Financial & Deposit Logs:** All payment updates must be performed with transactional consistency.
- **Record Disposal:** Patient discharge processes must securely purge active stay records according to institutional data retention policies.

---

## 4. Environment & Secrets Management
- Use environment variables or an external config `.properties` / `.env` file for database hosts, ports, usernames, and passwords rather than hardcoding credentials into Java source files.
- Refer to `.env.example` for environment variable configuration standards.
