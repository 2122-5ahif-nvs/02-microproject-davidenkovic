package at.htl.boundary;

import at.htl.entity.Artist;
import at.htl.entity.Song;
import at.htl.repository.ArtistRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("artists")
public class ArtistService {

    @Inject
    private ArtistRepository artistRepository;

    @Operation(
            summary = "Gets all interpretations",
            description = "Gets the artists from the repository"
    )

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArtists(){
        return Response.accepted(artistRepository.getArtists()).header("tag","list of artist").build();
    }

    @Path("/{labelName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSong(@PathParam("labelName") String labelName){
        List<Artist> songs = artistRepository.getArtistByLabel(labelName);
        return Response
                .accepted(songs)
                .header("tag","your song by" + labelName)
                .build();
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addArtist(Artist a){
        if(artistRepository.getArtists().contains(a))
        {
            return Response.ok("Artist: " + a.getArtistName() + " already exists!").build();
        }
        artistRepository.addArtist(a);
        return Response.ok("Artist: " + a.getArtistName() + " added!").build();
    }

    @Transactional
    @DELETE
    public Response deleteArtist() {
        boolean isDeleted = artistRepository.deleteArtist();
        if (!isDeleted) {
            return Response.ok("Error while deleting!").build();
        }
        return Response.ok("Artist deleted!").build();
    }

    @Transactional
    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateArtistByName(@PathParam("name") String name,Artist s){
        boolean isUpdated = artistRepository.updateArtist(name,s);
        if(!isUpdated){
            return Response.ok("Artist by " + name + " doesn't exist!").build();
        }
        return Response.ok("Artist by "+ name + " updated!").build();
    }

}
