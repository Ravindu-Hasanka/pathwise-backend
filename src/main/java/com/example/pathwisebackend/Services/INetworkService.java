package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Models.Connection;

public interface INetworkService {
    Connection requestConnection(Long userId, Long targetId);
    Connection acceptConnection( Long connectionId);
    Connection ignoreConnectionReq(Long connectionId);
}
