FROM maven:3.6.3-openjdk-15

COPY config/docker/java/docker-entrypoint /usr/local/bin/

ENTRYPOINT ["/usr/local/bin/docker-entrypoint"]

# Define working directory.
WORKDIR /var/www/bhp-backend

RUN mkdir /home/vagrant

# Add user vagrant
RUN adduser -u 1000 vagrant

RUN chown vagrant:vagrant /home/vagrant

USER vagrant
