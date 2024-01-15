package com.backend.services;
import com.backend.entities.Positions;
import com.backend.repositories.PositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionsRepository positionRepository;
    @Autowired
    public PositionService(PositionsRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
    public void addPosition(Positions positions) {
        positionRepository.save(positions);
    }

    public List<Positions> SearchPositionByUsername(String username) {

        Optional<List<Positions>> positionsOptional =positionRepository.findTrajetByUsername(username);
        if( positionsOptional.isPresent()){
            List<Positions> e = positionsOptional.get();
            return e;
            //
        }else{throw new IllegalStateException("Erreur");}

    }

    public Positions LastPosition(String username) {

        Optional<Positions> positionOptional =positionRepository.findLastPosByUsername(username);
        if( positionOptional.isPresent()){
            Positions e = positionOptional.get();
            return e;
            //
        }else{throw new IllegalStateException("Erreur");}

    }

    public double calculateHaversineDistance(String username1,String username2){
        double earthRadius = 6371000; // Rayon moyen de la Terre en mètres
        Optional<Positions> positionOptional1 =positionRepository.findLastPosByUsername(username1);
        Optional<Positions> positionOptional2 =positionRepository.findLastPosByUsername(username2);
        if( positionOptional1.isPresent() & positionOptional2.isPresent()){
            Positions p1 = positionOptional1.get();
            Positions p2 = positionOptional2.get();

            double lat1 = Math.toRadians(p1.getLatitude());
            double lon1 = Math.toRadians(p1.getLongitude());
            double lat2 = Math.toRadians(p2.getLatitude());
            double lon2 = Math.toRadians(p2.getLongitude());

            double dLat = lat2 - lat1;
            double dLon = lon2 - lon1;

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(lat1) * Math.cos(lat2) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return earthRadius * c;
            //
        }else{throw new IllegalStateException("Erreur de calcule de la distance de Haversine");}

    }


}