package io.cyberplum.client;

import io.cyberplum.client.frame.GameFrame;

public class Client {
    
    private static Client instance;
    
    private GameFrame gameFrame;
    private ClientEngine clientEngine;
    private boolean loaded;
    
    public static void main(String[] args) {
        new Client();
    }
    
    private Client() {
        Client.instance = this;
        
        loaded = false;
    
        gameFrame = new GameFrame();
        clientEngine = new ClientEngine();
        
        loaded = true;
    }
    
    public GameFrame getGameFrame() {
        return gameFrame;
    }
    
    public ClientEngine getClientEngine() {
        return clientEngine;
    }
    
    public boolean isLoaded() {
        return loaded;
    }
    
    public static Client getInstance() {
        return instance;
    }
    
}
