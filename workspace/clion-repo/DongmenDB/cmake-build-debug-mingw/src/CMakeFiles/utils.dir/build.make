# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.9

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\custom\develop\IDEA\JetBrains\CLion 2017.3.2\bin\cmake\bin\cmake.exe"

# The command to remove a file.
RM = "C:\custom\develop\IDEA\JetBrains\CLion 2017.3.2\bin\cmake\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = E:\workspace\work-repo\workspace\clion-repo\DongmenDB

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw

# Include any dependencies generated for this target.
include src/CMakeFiles/utils.dir/depend.make

# Include the progress variables for this target.
include src/CMakeFiles/utils.dir/progress.make

# Include the compile flags for this target's objects.
include src/CMakeFiles/utils.dir/flags.make

src/CMakeFiles/utils.dir/utils/arraylist.c.obj: src/CMakeFiles/utils.dir/flags.make
src/CMakeFiles/utils.dir/utils/arraylist.c.obj: src/CMakeFiles/utils.dir/includes_C.rsp
src/CMakeFiles/utils.dir/utils/arraylist.c.obj: ../src/utils/arraylist.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object src/CMakeFiles/utils.dir/utils/arraylist.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\utils.dir\utils\arraylist.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\arraylist.c

src/CMakeFiles/utils.dir/utils/arraylist.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/utils.dir/utils/arraylist.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\arraylist.c > CMakeFiles\utils.dir\utils\arraylist.c.i

src/CMakeFiles/utils.dir/utils/arraylist.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/utils.dir/utils/arraylist.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\arraylist.c -o CMakeFiles\utils.dir\utils\arraylist.c.s

src/CMakeFiles/utils.dir/utils/arraylist.c.obj.requires:

.PHONY : src/CMakeFiles/utils.dir/utils/arraylist.c.obj.requires

src/CMakeFiles/utils.dir/utils/arraylist.c.obj.provides: src/CMakeFiles/utils.dir/utils/arraylist.c.obj.requires
	$(MAKE) -f src\CMakeFiles\utils.dir\build.make src/CMakeFiles/utils.dir/utils/arraylist.c.obj.provides.build
.PHONY : src/CMakeFiles/utils.dir/utils/arraylist.c.obj.provides

src/CMakeFiles/utils.dir/utils/arraylist.c.obj.provides.build: src/CMakeFiles/utils.dir/utils/arraylist.c.obj


src/CMakeFiles/utils.dir/utils/hashmap.c.obj: src/CMakeFiles/utils.dir/flags.make
src/CMakeFiles/utils.dir/utils/hashmap.c.obj: src/CMakeFiles/utils.dir/includes_C.rsp
src/CMakeFiles/utils.dir/utils/hashmap.c.obj: ../src/utils/hashmap.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object src/CMakeFiles/utils.dir/utils/hashmap.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\utils.dir\utils\hashmap.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\hashmap.c

src/CMakeFiles/utils.dir/utils/hashmap.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/utils.dir/utils/hashmap.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\hashmap.c > CMakeFiles\utils.dir\utils\hashmap.c.i

src/CMakeFiles/utils.dir/utils/hashmap.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/utils.dir/utils/hashmap.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\hashmap.c -o CMakeFiles\utils.dir\utils\hashmap.c.s

src/CMakeFiles/utils.dir/utils/hashmap.c.obj.requires:

.PHONY : src/CMakeFiles/utils.dir/utils/hashmap.c.obj.requires

src/CMakeFiles/utils.dir/utils/hashmap.c.obj.provides: src/CMakeFiles/utils.dir/utils/hashmap.c.obj.requires
	$(MAKE) -f src\CMakeFiles\utils.dir\build.make src/CMakeFiles/utils.dir/utils/hashmap.c.obj.provides.build
.PHONY : src/CMakeFiles/utils.dir/utils/hashmap.c.obj.provides

src/CMakeFiles/utils.dir/utils/hashmap.c.obj.provides.build: src/CMakeFiles/utils.dir/utils/hashmap.c.obj


src/CMakeFiles/utils.dir/utils/opstack.c.obj: src/CMakeFiles/utils.dir/flags.make
src/CMakeFiles/utils.dir/utils/opstack.c.obj: src/CMakeFiles/utils.dir/includes_C.rsp
src/CMakeFiles/utils.dir/utils/opstack.c.obj: ../src/utils/opstack.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object src/CMakeFiles/utils.dir/utils/opstack.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\utils.dir\utils\opstack.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\opstack.c

src/CMakeFiles/utils.dir/utils/opstack.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/utils.dir/utils/opstack.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\opstack.c > CMakeFiles\utils.dir\utils\opstack.c.i

src/CMakeFiles/utils.dir/utils/opstack.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/utils.dir/utils/opstack.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\opstack.c -o CMakeFiles\utils.dir\utils\opstack.c.s

src/CMakeFiles/utils.dir/utils/opstack.c.obj.requires:

.PHONY : src/CMakeFiles/utils.dir/utils/opstack.c.obj.requires

src/CMakeFiles/utils.dir/utils/opstack.c.obj.provides: src/CMakeFiles/utils.dir/utils/opstack.c.obj.requires
	$(MAKE) -f src\CMakeFiles\utils.dir\build.make src/CMakeFiles/utils.dir/utils/opstack.c.obj.provides.build
.PHONY : src/CMakeFiles/utils.dir/utils/opstack.c.obj.provides

src/CMakeFiles/utils.dir/utils/opstack.c.obj.provides.build: src/CMakeFiles/utils.dir/utils/opstack.c.obj


src/CMakeFiles/utils.dir/utils/utils.c.obj: src/CMakeFiles/utils.dir/flags.make
src/CMakeFiles/utils.dir/utils/utils.c.obj: src/CMakeFiles/utils.dir/includes_C.rsp
src/CMakeFiles/utils.dir/utils/utils.c.obj: ../src/utils/utils.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building C object src/CMakeFiles/utils.dir/utils/utils.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\utils.dir\utils\utils.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\utils.c

src/CMakeFiles/utils.dir/utils/utils.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/utils.dir/utils/utils.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\utils.c > CMakeFiles\utils.dir\utils\utils.c.i

src/CMakeFiles/utils.dir/utils/utils.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/utils.dir/utils/utils.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\utils\utils.c -o CMakeFiles\utils.dir\utils\utils.c.s

src/CMakeFiles/utils.dir/utils/utils.c.obj.requires:

.PHONY : src/CMakeFiles/utils.dir/utils/utils.c.obj.requires

src/CMakeFiles/utils.dir/utils/utils.c.obj.provides: src/CMakeFiles/utils.dir/utils/utils.c.obj.requires
	$(MAKE) -f src\CMakeFiles\utils.dir\build.make src/CMakeFiles/utils.dir/utils/utils.c.obj.provides.build
.PHONY : src/CMakeFiles/utils.dir/utils/utils.c.obj.provides

src/CMakeFiles/utils.dir/utils/utils.c.obj.provides.build: src/CMakeFiles/utils.dir/utils/utils.c.obj


# Object files for target utils
utils_OBJECTS = \
"CMakeFiles/utils.dir/utils/arraylist.c.obj" \
"CMakeFiles/utils.dir/utils/hashmap.c.obj" \
"CMakeFiles/utils.dir/utils/opstack.c.obj" \
"CMakeFiles/utils.dir/utils/utils.c.obj"

# External object files for target utils
utils_EXTERNAL_OBJECTS =

../lib/libutilsd.a: src/CMakeFiles/utils.dir/utils/arraylist.c.obj
../lib/libutilsd.a: src/CMakeFiles/utils.dir/utils/hashmap.c.obj
../lib/libutilsd.a: src/CMakeFiles/utils.dir/utils/opstack.c.obj
../lib/libutilsd.a: src/CMakeFiles/utils.dir/utils/utils.c.obj
../lib/libutilsd.a: src/CMakeFiles/utils.dir/build.make
../lib/libutilsd.a: src/CMakeFiles/utils.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking C static library ..\..\lib\libutilsd.a"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && $(CMAKE_COMMAND) -P CMakeFiles\utils.dir\cmake_clean_target.cmake
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\utils.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
src/CMakeFiles/utils.dir/build: ../lib/libutilsd.a

.PHONY : src/CMakeFiles/utils.dir/build

src/CMakeFiles/utils.dir/requires: src/CMakeFiles/utils.dir/utils/arraylist.c.obj.requires
src/CMakeFiles/utils.dir/requires: src/CMakeFiles/utils.dir/utils/hashmap.c.obj.requires
src/CMakeFiles/utils.dir/requires: src/CMakeFiles/utils.dir/utils/opstack.c.obj.requires
src/CMakeFiles/utils.dir/requires: src/CMakeFiles/utils.dir/utils/utils.c.obj.requires

.PHONY : src/CMakeFiles/utils.dir/requires

src/CMakeFiles/utils.dir/clean:
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && $(CMAKE_COMMAND) -P CMakeFiles\utils.dir\cmake_clean.cmake
.PHONY : src/CMakeFiles/utils.dir/clean

src/CMakeFiles/utils.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" E:\workspace\work-repo\workspace\clion-repo\DongmenDB E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src\CMakeFiles\utils.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : src/CMakeFiles/utils.dir/depend
