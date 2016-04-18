# Mocs-Arcade
UTC Computer Science Senior Project 2016

This provides a way for users to download, update, and play video games designed by students and other developers.
This system uses Java for the back-end and JavaFX for the UI.

Libraries used:
- The information about the games (ie title, developer, description, etc.) is stored in a JSON file which is handled using Google's GSON library.
- The games are downloaded and updated using Eclipse's JGit library.
- Deleting/Uninstalling games is handled using Apache Common's IO library.
