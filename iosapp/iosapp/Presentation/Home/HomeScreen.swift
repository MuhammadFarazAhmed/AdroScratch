import SwiftUI
import shared

struct HomeScreen: View {
    
    @ObservedObject var state = ViewModels().getHomeViewModel().asObserveableObject()
    
    @State var moveToWebView = false
    
    var body: some View {
        
        NavigationView {
            VStack{
                
                
                switch state.state {
                    
                case is HomeScreenStateError:
                    
                    
                    
                    Text((state.state as! HomeScreenStateError).errorMessage)
                    
                    
                    
                    
                    
                case is HomeScreenStateSuccess:
                    
                    List{
                        ForEach((state.state as! HomeScreenStateSuccess).headlines , id: \.title){ item in
                        }
                        
                    }.frame( maxWidth: .infinity)
                        .listStyle(PlainListStyle())
                    
                    
                    
                case is HomeScreenStateLoading:
                    
                    ProgressView()
                    
                default:
                    Text("default")
                    
                    
                    
                }
            }.onAppear(perform: {
                state.viewModel.onIntent(intent: HomeScreenSideEvent.getHome())
            }).navigationBarTitleDisplayMode(.large)
                .toolbar {
                    ToolbarItem(placement: .principal) {
                        VStack {
                            Text("Headlines").font(.headline)
                            
                        }
                    }
                    
                    ToolbarItem(placement: .primaryAction) {
//                     NavigationLink(destination: ReadLaterScreen()) {
//                            Image(systemName: "bookmark.circle.fill")
//                        }.buttonStyle(PlainButtonStyle())
//                        
                    }
                }
        }
    }
}

struct Screen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
