# Fitness Studio Management Software

This project is a management software for a fitness studio, developed using Java and PostgreSQL. It does not use an ORM (Object-Relational Mapping) framework.

## Features

- **Check-in/Check-out System**: Allows members to check in and check out of the fitness studio.
- **Membership Management**: Supports various types of memberships (Schüler, Standard, Premium, VIP, Gast, Abgemeldet).
- **Course Management**: Optional feature to manage fitness courses. If enabled, members can enroll in these courses.
- **Trainer Management**: Optional feature to manage trainers. If enabled, each trainer can either conduct fitness courses or provide personal training, but not both.

## Usage

The software provides a command-line interface for interaction. The user is prompted to enter the necessary information for each operation.

### Adding a Member

To add a member, the user is prompted to enter the member's first name, last name, birthdate, and type of membership. The membership type must be one of the predefined types (Schüler, Standard, Premium, VIP, Gast, Abgemeldet).

### Adding a Trainer

To add a trainer, the user is prompted to enter the trainer's first name, last name, birthdate, and salary.

### Adding a Course

To add a course, the user is prompted to enter the course name, the first and last name of the trainer, the start and end times of the course, and the day of the week when the course is held.

### Adding a Course Participant

To add a participant to a course, the user is prompted to enter the first and last name of the member and the name of the course.

## Development

This project uses Java and PostgreSQL. It is built with Maven.

## License

This project is open source under the MIT license.