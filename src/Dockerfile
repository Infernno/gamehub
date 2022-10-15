FROM jenkins/jenkins:2.361.1-jdk11

ENV DEBIAN_FRONTEND noninteractive
ENV LANG 'en_US.UTF-8'

USER root

RUN dpkg --add-architecture i386 && apt-get update -yqq && apt-get install -y \
  curl \
  expect \
  git \
  make \
  git-core \
  libc6:i386 \
  libgcc1:i386 \
  libncurses5:i386 \
  libstdc++6:i386 \
  zlib1g:i386 \
  openjdk-11-jdk \
  wget \
  unzip \
  vim \
  openssh-client \
  locales \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* \
  && localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8

ENV ANDROID_SDK_ROOT "/sdk"

RUN mkdir ${ANDROID_SDK_ROOT}
RUN chown -R jenkins ${ANDROID_SDK_ROOT}

ENV VERSION_SDK_TOOLS "8092744"

ENV GRADLE_PROFILER "/gradle-profiler/gradle-profiler-0.19.0"

ENV PATH "${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/cmdline-tools/tools/bin:${ANDROID_SDK_ROOT}/platform-tools:${ANDROID_SDK_ROOT}/emulator:${GRADLE_PROFILER}/bin"

RUN mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-${VERSION_SDK_TOOLS}_latest.zip && \
    unzip *tools*linux*.zip -d ${ANDROID_SDK_ROOT} && \
    rm *tools*linux*.zip

RUN ${ANDROID_SDK_ROOT}/cmdline-tools/bin/sdkmanager --update --sdk_root=${ANDROID_SDK_ROOT}

COPY gradle-profiler-0.19.0.zip gradle-profiler/

RUN unzip gradle-profiler/gradle-profiler-0.19.0.zip -d gradle-profiler && rm -v gradle-profiler/gradle-profiler-0.19.0.zip

RUN yes | ${ANDROID_SDK_ROOT}/cmdline-tools/bin/sdkmanager --licenses --sdk_root=${ANDROID_SDK_ROOT}

RUN apt-get update && apt-get install -y lsb-release

RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
  https://download.docker.com/linux/debian/gpg

RUN echo "deb [arch=$(dpkg --print-architecture) \
  signed-by=/usr/share/keyrings/docker-archive-keyring.asc] \
  https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list

RUN apt-get update && apt-get install -y docker-ce-cli

USER jenkins