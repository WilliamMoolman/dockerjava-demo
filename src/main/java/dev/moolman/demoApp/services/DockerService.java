package dev.moolman.demoApp.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DockerService {

    private final DockerClient dockerClient;

    public DockerService() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://127.0.0.1:2375") // docker daemon.json must include the tcp listen host
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();

        this.dockerClient= DockerClientImpl.getInstance(config, httpClient);
    }

    public void runContainer(String imageName, String containerName) {
        HostConfig hostConfig = HostConfig.newHostConfig()
//                .withExtraHosts("host.docker.internal:host-gateway")
                .withNetworkMode("host");

        dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(hostConfig)
                .exec();
        dockerClient.startContainerCmd(containerName).exec();
    }
}