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
include src/CMakeFiles/dongmensql.dir/depend.make

# Include the progress variables for this target.
include src/CMakeFiles/dongmensql.dir/progress.make

# Include the compile flags for this target's objects.
include src/CMakeFiles/dongmensql.dir/flags.make

src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj: ../src/dongmensql/column.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\column.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\column.c

src/CMakeFiles/dongmensql.dir/dongmensql/column.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/column.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\column.c > CMakeFiles\dongmensql.dir\dongmensql\column.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/column.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/column.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\column.c -o CMakeFiles\dongmensql.dir\dongmensql\column.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj: ../src/dongmensql/common.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\common.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\common.c

src/CMakeFiles/dongmensql.dir/dongmensql/common.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/common.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\common.c > CMakeFiles\dongmensql.dir\dongmensql\common.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/common.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/common.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\common.c -o CMakeFiles\dongmensql.dir\dongmensql\common.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj: ../src/dongmensql/create.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\create.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\create.c

src/CMakeFiles/dongmensql.dir/dongmensql/create.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/create.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\create.c > CMakeFiles\dongmensql.dir\dongmensql\create.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/create.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/create.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\create.c -o CMakeFiles\dongmensql.dir\dongmensql\create.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj: ../src/dongmensql/delete.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\delete.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\delete.c

src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/delete.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\delete.c > CMakeFiles\dongmensql.dir\dongmensql\delete.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/delete.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\delete.c -o CMakeFiles\dongmensql.dir\dongmensql\delete.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj: ../src/dongmensql/insert.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\insert.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\insert.c

src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/insert.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\insert.c > CMakeFiles\dongmensql.dir\dongmensql\insert.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/insert.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\insert.c -o CMakeFiles\dongmensql.dir\dongmensql\insert.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj: ../src/dongmensql/literal.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\literal.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\literal.c

src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/literal.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\literal.c > CMakeFiles\dongmensql.dir\dongmensql\literal.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/literal.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\literal.c -o CMakeFiles\dongmensql.dir\dongmensql\literal.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj: ../src/dongmensql/queryplan.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\queryplan.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\queryplan.c

src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\queryplan.c > CMakeFiles\dongmensql.dir\dongmensql\queryplan.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\queryplan.c -o CMakeFiles\dongmensql.dir\dongmensql\queryplan.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj: ../src/dongmensql/ra.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_8) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\ra.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\ra.c

src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/ra.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\ra.c > CMakeFiles\dongmensql.dir\dongmensql\ra.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/ra.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\ra.c -o CMakeFiles\dongmensql.dir\dongmensql\ra.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj: ../src/dongmensql/row.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_9) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\row.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\row.c

src/CMakeFiles/dongmensql.dir/dongmensql/row.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/row.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\row.c > CMakeFiles\dongmensql.dir\dongmensql\row.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/row.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/row.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\row.c -o CMakeFiles\dongmensql.dir\dongmensql\row.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj


src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj: ../src/dongmensql/sra.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_10) "Building C object src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\dongmensql\sra.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\sra.c

src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/dongmensql/sra.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\sra.c > CMakeFiles\dongmensql.dir\dongmensql\sra.c.i

src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/dongmensql/sra.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src\dongmensql\sra.c -o CMakeFiles\dongmensql.dir\dongmensql\sra.c.s

src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.requires

src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.provides: src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.provides

src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj


src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj: src/CMakeFiles/dongmensql.dir/flags.make
src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj: src/CMakeFiles/dongmensql.dir/includes_C.rsp
src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj: ../src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_11) "Building C object src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\dongmensql.dir\__\src_experiment\exp_05_algebra_opt\exp_05_02_condition_push_down.c.obj   -c E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src_experiment\exp_05_algebra_opt\exp_05_02_condition_push_down.c

src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.i"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src_experiment\exp_05_algebra_opt\exp_05_02_condition_push_down.c > CMakeFiles\dongmensql.dir\__\src_experiment\exp_05_algebra_opt\exp_05_02_condition_push_down.c.i

src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.s"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && C:\custom\develop\CONFIG\mingw\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src_experiment\exp_05_algebra_opt\exp_05_02_condition_push_down.c -o CMakeFiles\dongmensql.dir\__\src_experiment\exp_05_algebra_opt\exp_05_02_condition_push_down.c.s

src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.requires:

.PHONY : src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.requires

src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.provides: src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.requires
	$(MAKE) -f src\CMakeFiles\dongmensql.dir\build.make src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.provides.build
.PHONY : src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.provides

src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.provides.build: src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj


# Object files for target dongmensql
dongmensql_OBJECTS = \
"CMakeFiles/dongmensql.dir/dongmensql/column.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/common.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/create.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/row.c.obj" \
"CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj" \
"CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj"

# External object files for target dongmensql
dongmensql_EXTERNAL_OBJECTS =

../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/build.make
../lib/libdongmensqld.a: src/CMakeFiles/dongmensql.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\CMakeFiles --progress-num=$(CMAKE_PROGRESS_12) "Linking C static library ..\..\lib\libdongmensqld.a"
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && $(CMAKE_COMMAND) -P CMakeFiles\dongmensql.dir\cmake_clean_target.cmake
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\dongmensql.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
src/CMakeFiles/dongmensql.dir/build: ../lib/libdongmensqld.a

.PHONY : src/CMakeFiles/dongmensql.dir/build

src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/column.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/common.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/create.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/delete.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/insert.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/literal.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/queryplan.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/ra.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/row.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/dongmensql/sra.c.obj.requires
src/CMakeFiles/dongmensql.dir/requires: src/CMakeFiles/dongmensql.dir/__/src_experiment/exp_05_algebra_opt/exp_05_02_condition_push_down.c.obj.requires

.PHONY : src/CMakeFiles/dongmensql.dir/requires

src/CMakeFiles/dongmensql.dir/clean:
	cd /d E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src && $(CMAKE_COMMAND) -P CMakeFiles\dongmensql.dir\cmake_clean.cmake
.PHONY : src/CMakeFiles/dongmensql.dir/clean

src/CMakeFiles/dongmensql.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" E:\workspace\work-repo\workspace\clion-repo\DongmenDB E:\workspace\work-repo\workspace\clion-repo\DongmenDB\src E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src E:\workspace\work-repo\workspace\clion-repo\DongmenDB\cmake-build-debug-mingw\src\CMakeFiles\dongmensql.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : src/CMakeFiles/dongmensql.dir/depend
