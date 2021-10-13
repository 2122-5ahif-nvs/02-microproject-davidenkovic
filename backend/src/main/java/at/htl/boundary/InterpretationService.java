package at.htl.boundary;

import at.htl.entity.Interpretation;
import at.htl.repository.InterpretationRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("interpretations")
public class InterpretationService {

    @Inject
    private InterpretationRepository interpretationRepository;

    @Operation(
            summary = "Gets all interpretations",
            description = "Gets the interpretations from the repository"
    )

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInterpretations() {
        return Response.accepted(interpretationRepository.getInterpretations()).header("tag", "list of artist").build();
    }

    @Path("/{artistName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInterpretation(@PathParam("artistName") String artistName) {
        List<Interpretation> songs = interpretationRepository.getInterpretationsArtistName(artistName);
        return Response
                .accepted(songs)
                .header("tag", "your Interpretation by " + artistName)
                .build();
    }


    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInterpretation(Interpretation i){
        if(interpretationRepository.getInterpretations().contains(i))
        {
            return Response.ok("Artist: " + i.getArtist().getArtistName() + " already exists!").build();
        }
        interpretationRepository.addInterpretation(i);
        return Response.ok("Artist: " + i.getArtist().getArtistName() + " added!").build();
    }

    @Transactional
    @DELETE
    public Response deleteArtist() {
        boolean isDeleted = interpretationRepository.deleteInterpretation();
        if (!isDeleted) {
            return Response.ok("Error while deleting!").build();
        }
        return Response.ok("Artist deleted!").build();
    }

    /*@Transactional
    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArtistByName(@PathParam("name") String name,Interpretation s){
        boolean isUpdated = interpretationRepository.updateInterpretationsByArtist(name,s);
        if(!isUpdated){
            return Response.ok("Artist by " + name + " doesn't exist!").build();
        }
        return Response.ok("Artist by "+ name + " updated!").build();
    }*/
}
