package at.htl.boundary;

import at.htl.entity.Artist;
import at.htl.entity.Song;
import at.htl.repository.ArtistRepository;
import at.htl.repository.SongRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/songs")
public class SongService {
    @Inject
    private SongRepository songRepository;

    @Operation(
            summary = "Gets all songs",
            description = "Gets the songs from the repository"
    )

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSongs(){
        return Response
                .accepted(songRepository.getSongs())
                .header("tag","list of Songs")
                .build();
    }

    @Path("/name")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSong(@QueryParam("name") String name){
        List<Song> songs = songRepository.getSongsByArtistName(name);
        return Response
                .accepted(songs)
                .header("tag","your song by" + name)
                .build();
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSong(Song s){
        if(songRepository.getSongs().contains(s))
        {
            return Response.ok("Song: " + s.getSongname() + " already exists!").build();
        }
        songRepository.addSong(s);
        return Response.ok("Song: " + s.getSongname() + " added!").build();
    }

    @Transactional
    @DELETE
    public Response deleteSong() {
        boolean isDeleted = songRepository.deleteSong();
        if (!isDeleted) {
            return Response.ok("Error while deleting!").build();
        }
        return Response.ok("Song deleted!").build();
    }

    @Transactional
    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSongByName(@PathParam("name") String name,Song s){
        boolean isUpdated = songRepository.updateSong(name,s);
        if(!isUpdated){
            return Response.ok("Song by " + name + " doesn't exist!").build();
        }
        return Response.ok("Song by "+ name + " updated!").build();
    }

}
