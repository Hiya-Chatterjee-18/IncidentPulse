# Structured Application Logging Architecture

The platform uses **SLF4J + Logback** to generate structured JSON logs.

### Key Features:
- Log rotation and console formatting.
- TCP socket streaming to **Logstash** on port 5000.
- Exception traceback tracking with unique event IDs.
