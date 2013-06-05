
package edu.uoregon.cs.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.uoregon.cs.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetMembersResponse_QNAME = new QName("http://cs.uoregon.edu/", "getMembersResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://cs.uoregon.edu/", "getUserResponse");
    private final static QName _RegisterResponse_QNAME = new QName("http://cs.uoregon.edu/", "registerResponse");
    private final static QName _AuthenticateResponse_QNAME = new QName("http://cs.uoregon.edu/", "authenticateResponse");
    private final static QName _RemoveMember_QNAME = new QName("http://cs.uoregon.edu/", "removeMember");
    private final static QName _GetGroup_QNAME = new QName("http://cs.uoregon.edu/", "getGroup");
    private final static QName _GetUserByIDResponse_QNAME = new QName("http://cs.uoregon.edu/", "getUserByIDResponse");
    private final static QName _AddMember_QNAME = new QName("http://cs.uoregon.edu/", "addMember");
    private final static QName _CreateGroupResponse_QNAME = new QName("http://cs.uoregon.edu/", "createGroupResponse");
    private final static QName _SetLocationResponse_QNAME = new QName("http://cs.uoregon.edu/", "setLocationResponse");
    private final static QName _AddMemberResponse_QNAME = new QName("http://cs.uoregon.edu/", "addMemberResponse");
    private final static QName _GetHistoryResponse_QNAME = new QName("http://cs.uoregon.edu/", "getHistoryResponse");
    private final static QName _Register_QNAME = new QName("http://cs.uoregon.edu/", "register");
    private final static QName _GetLocation_QNAME = new QName("http://cs.uoregon.edu/", "getLocation");
    private final static QName _GetGroupResponse_QNAME = new QName("http://cs.uoregon.edu/", "getGroupResponse");
    private final static QName _SetLocation_QNAME = new QName("http://cs.uoregon.edu/", "setLocation");
    private final static QName _Authenticate_QNAME = new QName("http://cs.uoregon.edu/", "authenticate");
    private final static QName _CreateGroup_QNAME = new QName("http://cs.uoregon.edu/", "createGroup");
    private final static QName _GetMembers_QNAME = new QName("http://cs.uoregon.edu/", "getMembers");
    private final static QName _GetHistory_QNAME = new QName("http://cs.uoregon.edu/", "getHistory");
    private final static QName _GetLocationResponse_QNAME = new QName("http://cs.uoregon.edu/", "getLocationResponse");
    private final static QName _RemoveMemberResponse_QNAME = new QName("http://cs.uoregon.edu/", "removeMemberResponse");
    private final static QName _GetGroupsResponse_QNAME = new QName("http://cs.uoregon.edu/", "getGroupsResponse");
    private final static QName _GetUserByID_QNAME = new QName("http://cs.uoregon.edu/", "getUserByID");
    private final static QName _GetUser_QNAME = new QName("http://cs.uoregon.edu/", "getUser");
    private final static QName _GetGroups_QNAME = new QName("http://cs.uoregon.edu/", "getGroups");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.uoregon.cs.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserByID }
     * 
     */
    public GetUserByID createGetUserByID() {
        return new GetUserByID();
    }

    /**
     * Create an instance of {@link GetHistory }
     * 
     */
    public GetHistory createGetHistory() {
        return new GetHistory();
    }

    /**
     * Create an instance of {@link GetLocationResponse }
     * 
     */
    public GetLocationResponse createGetLocationResponse() {
        return new GetLocationResponse();
    }

    /**
     * Create an instance of {@link SetLocation }
     * 
     */
    public SetLocation createSetLocation() {
        return new SetLocation();
    }

    /**
     * Create an instance of {@link Authenticate }
     * 
     */
    public Authenticate createAuthenticate() {
        return new Authenticate();
    }

    /**
     * Create an instance of {@link CreateGroup }
     * 
     */
    public CreateGroup createCreateGroup() {
        return new CreateGroup();
    }

    /**
     * Create an instance of {@link AddMemberResponse }
     * 
     */
    public AddMemberResponse createAddMemberResponse() {
        return new AddMemberResponse();
    }

    /**
     * Create an instance of {@link GetGroup }
     * 
     */
    public GetGroup createGetGroup() {
        return new GetGroup();
    }

    /**
     * Create an instance of {@link GetUserByIDResponse }
     * 
     */
    public GetUserByIDResponse createGetUserByIDResponse() {
        return new GetUserByIDResponse();
    }

    /**
     * Create an instance of {@link GetMembersResponse }
     * 
     */
    public GetMembersResponse createGetMembersResponse() {
        return new GetMembersResponse();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link RemoveMember }
     * 
     */
    public RemoveMember createRemoveMember() {
        return new RemoveMember();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GetGroups }
     * 
     */
    public GetGroups createGetGroups() {
        return new GetGroups();
    }

    /**
     * Create an instance of {@link RemoveMemberResponse }
     * 
     */
    public RemoveMemberResponse createRemoveMemberResponse() {
        return new RemoveMemberResponse();
    }

    /**
     * Create an instance of {@link GetGroupsResponse }
     * 
     */
    public GetGroupsResponse createGetGroupsResponse() {
        return new GetGroupsResponse();
    }

    /**
     * Create an instance of {@link GetMembers }
     * 
     */
    public GetMembers createGetMembers() {
        return new GetMembers();
    }

    /**
     * Create an instance of {@link GetGroupResponse }
     * 
     */
    public GetGroupResponse createGetGroupResponse() {
        return new GetGroupResponse();
    }

    /**
     * Create an instance of {@link GetLocation }
     * 
     */
    public GetLocation createGetLocation() {
        return new GetLocation();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link AddMember }
     * 
     */
    public AddMember createAddMember() {
        return new AddMember();
    }

    /**
     * Create an instance of {@link SetLocationResponse }
     * 
     */
    public SetLocationResponse createSetLocationResponse() {
        return new SetLocationResponse();
    }

    /**
     * Create an instance of {@link CreateGroupResponse }
     * 
     */
    public CreateGroupResponse createCreateGroupResponse() {
        return new CreateGroupResponse();
    }

    /**
     * Create an instance of {@link GetHistoryResponse }
     * 
     */
    public GetHistoryResponse createGetHistoryResponse() {
        return new GetHistoryResponse();
    }

    /**
     * Create an instance of {@link AuthenticateResponse }
     * 
     */
    public AuthenticateResponse createAuthenticateResponse() {
        return new AuthenticateResponse();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link Status }
     * 
     */
    public Status createStatus() {
        return new Status();
    }

    /**
     * Create an instance of {@link UserStatus }
     * 
     */
    public UserStatus createUserStatus() {
        return new UserStatus();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMembersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getMembersResponse")
    public JAXBElement<GetMembersResponse> createGetMembersResponse(GetMembersResponse value) {
        return new JAXBElement<GetMembersResponse>(_GetMembersResponse_QNAME, GetMembersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "authenticateResponse")
    public JAXBElement<AuthenticateResponse> createAuthenticateResponse(AuthenticateResponse value) {
        return new JAXBElement<AuthenticateResponse>(_AuthenticateResponse_QNAME, AuthenticateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveMember }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "removeMember")
    public JAXBElement<RemoveMember> createRemoveMember(RemoveMember value) {
        return new JAXBElement<RemoveMember>(_RemoveMember_QNAME, RemoveMember.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGroup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getGroup")
    public JAXBElement<GetGroup> createGetGroup(GetGroup value) {
        return new JAXBElement<GetGroup>(_GetGroup_QNAME, GetGroup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getUserByIDResponse")
    public JAXBElement<GetUserByIDResponse> createGetUserByIDResponse(GetUserByIDResponse value) {
        return new JAXBElement<GetUserByIDResponse>(_GetUserByIDResponse_QNAME, GetUserByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMember }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "addMember")
    public JAXBElement<AddMember> createAddMember(AddMember value) {
        return new JAXBElement<AddMember>(_AddMember_QNAME, AddMember.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGroupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "createGroupResponse")
    public JAXBElement<CreateGroupResponse> createCreateGroupResponse(CreateGroupResponse value) {
        return new JAXBElement<CreateGroupResponse>(_CreateGroupResponse_QNAME, CreateGroupResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "setLocationResponse")
    public JAXBElement<SetLocationResponse> createSetLocationResponse(SetLocationResponse value) {
        return new JAXBElement<SetLocationResponse>(_SetLocationResponse_QNAME, SetLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMemberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "addMemberResponse")
    public JAXBElement<AddMemberResponse> createAddMemberResponse(AddMemberResponse value) {
        return new JAXBElement<AddMemberResponse>(_AddMemberResponse_QNAME, AddMemberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHistoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getHistoryResponse")
    public JAXBElement<GetHistoryResponse> createGetHistoryResponse(GetHistoryResponse value) {
        return new JAXBElement<GetHistoryResponse>(_GetHistoryResponse_QNAME, GetHistoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getLocation")
    public JAXBElement<GetLocation> createGetLocation(GetLocation value) {
        return new JAXBElement<GetLocation>(_GetLocation_QNAME, GetLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGroupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getGroupResponse")
    public JAXBElement<GetGroupResponse> createGetGroupResponse(GetGroupResponse value) {
        return new JAXBElement<GetGroupResponse>(_GetGroupResponse_QNAME, GetGroupResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "setLocation")
    public JAXBElement<SetLocation> createSetLocation(SetLocation value) {
        return new JAXBElement<SetLocation>(_SetLocation_QNAME, SetLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authenticate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "authenticate")
    public JAXBElement<Authenticate> createAuthenticate(Authenticate value) {
        return new JAXBElement<Authenticate>(_Authenticate_QNAME, Authenticate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGroup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "createGroup")
    public JAXBElement<CreateGroup> createCreateGroup(CreateGroup value) {
        return new JAXBElement<CreateGroup>(_CreateGroup_QNAME, CreateGroup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMembers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getMembers")
    public JAXBElement<GetMembers> createGetMembers(GetMembers value) {
        return new JAXBElement<GetMembers>(_GetMembers_QNAME, GetMembers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getHistory")
    public JAXBElement<GetHistory> createGetHistory(GetHistory value) {
        return new JAXBElement<GetHistory>(_GetHistory_QNAME, GetHistory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getLocationResponse")
    public JAXBElement<GetLocationResponse> createGetLocationResponse(GetLocationResponse value) {
        return new JAXBElement<GetLocationResponse>(_GetLocationResponse_QNAME, GetLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveMemberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "removeMemberResponse")
    public JAXBElement<RemoveMemberResponse> createRemoveMemberResponse(RemoveMemberResponse value) {
        return new JAXBElement<RemoveMemberResponse>(_RemoveMemberResponse_QNAME, RemoveMemberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGroupsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getGroupsResponse")
    public JAXBElement<GetGroupsResponse> createGetGroupsResponse(GetGroupsResponse value) {
        return new JAXBElement<GetGroupsResponse>(_GetGroupsResponse_QNAME, GetGroupsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getUserByID")
    public JAXBElement<GetUserByID> createGetUserByID(GetUserByID value) {
        return new JAXBElement<GetUserByID>(_GetUserByID_QNAME, GetUserByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGroups }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs.uoregon.edu/", name = "getGroups")
    public JAXBElement<GetGroups> createGetGroups(GetGroups value) {
        return new JAXBElement<GetGroups>(_GetGroups_QNAME, GetGroups.class, null, value);
    }

}
